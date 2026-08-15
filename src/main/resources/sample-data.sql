-- Run this script after the application has started at least once so that
-- Hibernate has created the questions table.
BEGIN;

WITH seed_questions (
    question_title,
    option1,
    option2,
    option3,
    option4,
    right_answer,
    category,
    difficulty_level
) AS (
    VALUES
        ('Which keyword prevents a Java method from being overridden?',
         'static', 'final', 'abstract', 'synchronized',
         'final', 'Java', 'Easy'),
        ('Which Java collection does not allow duplicate elements?',
         'ArrayList', 'LinkedList', 'HashSet', 'Vector',
         'HashSet', 'Java', 'Easy'),
        ('Which method starts a new Java thread?',
         'run()', 'start()', 'execute()', 'init()',
         'start()', 'Java', 'Easy'),
        ('Which primitive Java type stores a 64-bit signed integer?',
         'int', 'short', 'long', 'double',
         'long', 'Java', 'Easy'),
        ('Which interface must a resource implement for try-with-resources?',
         'Serializable', 'Runnable', 'Cloneable', 'AutoCloseable',
         'AutoCloseable', 'Java', 'Medium'),
        ('What is the default value of a boolean instance field in Java?',
         'true', 'false', 'null', '0',
         'false', 'Java', 'Easy'),
        ('Which operation is an intermediate Java Stream operation?',
         'collect()', 'forEach()', 'filter()', 'count()',
         'filter()', 'Java', 'Medium'),

        ('Which SQL command removes all rows while keeping the table structure?',
         'DROP TABLE', 'TRUNCATE TABLE', 'ALTER TABLE', 'CREATE TABLE',
         'TRUNCATE TABLE', 'SQL', 'Easy'),
        ('Which SQL clause filters results after GROUP BY?',
         'WHERE', 'ORDER BY', 'HAVING', 'LIMIT',
         'HAVING', 'SQL', 'Easy'),
        ('Which join returns only rows that match in both tables?',
         'LEFT JOIN', 'RIGHT JOIN', 'FULL JOIN', 'INNER JOIN',
         'INNER JOIN', 'SQL', 'Easy'),
        ('How does COUNT(column_name) treat NULL values?',
         'It counts them', 'It ignores them', 'It returns NULL', 'It raises an error',
         'It ignores them', 'SQL', 'Medium'),
        ('Which normal form removes partial dependencies on a composite key?',
         'First normal form', 'Second normal form', 'Third normal form', 'Boyce-Codd normal form',
         'Second normal form', 'SQL', 'Medium'),
        ('Which isolation level prevents dirty reads but can allow non-repeatable reads?',
         'Read uncommitted', 'Read committed', 'Repeatable read', 'Serializable',
         'Read committed', 'SQL', 'Hard'),
        ('Which SQL window function ranks rows without gaps after ties?',
         'ROW_NUMBER()', 'RANK()', 'DENSE_RANK()', 'NTILE()',
         'DENSE_RANK()', 'SQL', 'Medium'),

        ('Which Spring annotation marks a service-layer component?',
         '@Component', '@Service', '@Repository', '@Controller',
         '@Service', 'Spring', 'Easy'),
        ('What is the default scope of a Spring bean?',
         'prototype', 'request', 'session', 'singleton',
         'singleton', 'Spring', 'Easy'),
        ('Which file is commonly used for Spring Boot application configuration?',
         'pom.properties', 'application.properties', 'spring.config', 'bootstrap.java',
         'application.properties', 'Spring', 'Easy'),
        ('Which annotation defines a transactional boundary in Spring?',
         '@Transactional', '@Validated', '@Async', '@Scheduled',
         '@Transactional', 'Spring', 'Medium'),
        ('Which Spring Data interface provides CRUD and pagination support for JPA entities?',
         'CrudTemplate', 'EntityRepository', 'JpaRepository', 'PersistenceManager',
         'JpaRepository', 'Spring', 'Medium'),
        ('Which dependency injection style is generally recommended in Spring?',
         'Field injection', 'Constructor injection', 'Static injection', 'Method lookup injection',
         'Constructor injection', 'Spring', 'Medium')
)

INSERT INTO questions (
    question_title,
    option1,
    option2,
    option3,
    option4,
    right_answer,
    category,
    difficulty_level
)
SELECT
    seed.question_title,
    seed.option1,
    seed.option2,
    seed.option3,
    seed.option4,
    seed.right_answer,
    seed.category,
    seed.difficulty_level
FROM seed_questions seed
WHERE NOT EXISTS (
    SELECT 1
    FROM questions existing
    WHERE existing.question_title = seed.question_title
);

COMMIT;

-- Verify the seeded question distribution.
SELECT category, difficulty_level, COUNT(*) AS question_count
FROM questions
WHERE category IN ('Java', 'SQL', 'Spring')
GROUP BY category, difficulty_level
ORDER BY category, difficulty_level;
