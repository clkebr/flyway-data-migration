-- Update user roles based on some logic
UPDATE users
SET role_id = (SELECT id FROM roles WHERE role_name = 'USER')
WHERE role_id IS NULL;

-- Add a new column for storing computed values if it does not already exist
DO $$
    BEGIN
        IF NOT EXISTS (SELECT 1 FROM information_schema.columns
                       WHERE table_name = 'users' AND column_name = 'full_name') THEN
            ALTER TABLE users ADD COLUMN full_name VARCHAR(100);
        END IF;
    END $$;

-- Compute and update full_name column
UPDATE users
SET full_name = COALESCE(
        CASE
            WHEN TRIM(first_name) = '' AND TRIM(last_name) = '' THEN NULL
            ELSE TRIM(first_name) || ' ' || TRIM(last_name)
            END,
        username
    )
WHERE full_name IS NULL OR full_name = '';


-- Create a materialized view for performance optimization if it does not already exist
DO $$
    BEGIN
        IF NOT EXISTS (SELECT 1 FROM pg_matviews WHERE matviewname = 'user_role_summary') THEN
            CREATE MATERIALIZED VIEW user_role_summary AS
            SELECT u.id AS user_id, u.username, r.role_name
            FROM users u
                     JOIN roles r ON u.role_id = r.id;
        END IF;
    END $$;

-- Refresh materialized view (run periodically)
REFRESH MATERIALIZED VIEW user_role_summary;

-- Example of conditional insert based on existing data with error handling
DO $$
    BEGIN
        IF NOT EXISTS (SELECT 1 FROM roles WHERE role_name = 'SUPERVISOR') THEN
            INSERT INTO roles (role_name, description, created_at)
            VALUES ('SUPERVISOR', 'Supervisor role with additional permissions', CURRENT_TIMESTAMP);
        END IF;
    EXCEPTION
        WHEN others THEN
            RAISE NOTICE 'An error occurred while inserting role SUPERVISOR.';
    END $$;
