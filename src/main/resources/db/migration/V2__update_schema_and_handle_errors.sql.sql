-- Add new column to roles table if it does not already exist
DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name = 'roles' AND column_name = 'last_update_user_id') THEN
ALTER TABLE roles ADD COLUMN last_update_user_id BIGINT;
END IF;
EXCEPTION
    WHEN others THEN
        RAISE NOTICE 'An error occurred while adding column last_update_user_id.';
END $$;

-- Update role description
UPDATE roles
SET description = 'Updated role description'
WHERE role_name = 'ADMIN';

-- Example of potential error handling with conditional logic
DO $$
BEGIN
    -- Check if a column exists before trying to modify it
    IF EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='users' AND column_name='phone_number') THEN
ALTER TABLE users DROP COLUMN phone_number;
END IF;
EXCEPTION
    WHEN others THEN
        RAISE NOTICE 'An error occurred during schema update.';
END $$;

-- Example of conditional data insertion to avoid duplication
DO $$
BEGIN
    -- Insert a new role only if it does not already exist
    IF NOT EXISTS (SELECT 1 FROM roles WHERE role_name = 'MANAGER') THEN
        INSERT INTO roles (role_name, description, created_at)
        VALUES ('MANAGER', 'Manager role with additional permissions', CURRENT_TIMESTAMP);
END IF;
EXCEPTION
    WHEN others THEN
        RAISE NOTICE 'An error occurred while inserting role MANAGER.';
END $$;
