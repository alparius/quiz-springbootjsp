CREATE DATABASE quiz_spring
        GO

USE quiz_spring
    GO

CREATE LOGIN quiz_user WITH PASSWORD = 'password'
GO

IF NOT EXISTS (SELECT * FROM sys.database_principals WHERE name = N'quiz_user')
BEGIN
CREATE USER [quiz_user] FOR LOGIN [quiz_user]
    EXEC sp_addrolemember N'db_owner', N'quiz_user'
    END
    GO