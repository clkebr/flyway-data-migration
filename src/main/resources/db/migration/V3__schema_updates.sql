-- Add new column to roles table if it does not already exist
DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name = 'roles' AND column_name = 'updated_at') THEN
ALTER TABLE roles ADD COLUMN updated_at TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP;
END IF;
EXCEPTION
    WHEN others THEN
        RAISE NOTICE 'An error occurred while adding column updated_at.';
END $$;

-- Continue with other schema updates or data manipulations

-- Example: Update role description
UPDATE roles
SET description = 'Updated role description'
WHERE role_name = 'ADMIN';

-- Example: Add a new role if it does not already exist
DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM roles WHERE role_name = 'MANAGER') THEN
        INSERT INTO roles (role_name, description, created_at)
        VALUES ('MANAGER', 'Manager role with additional permissions', CURRENT_TIMESTAMP);
END IF;
EXCEPTION
    WHEN others THEN
        RAISE NOTICE 'An error occurred while inserting role MANAGER.';
END $$;
