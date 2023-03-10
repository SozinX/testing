package org.sozinx.constant;

/**
 * A class with a list of queries for database which used in code.
 *
 * @author Ostap Petruniak
 * @since 1.0
 */

public class QueryConst {
    /**
     * Role table
     */
    public static final String GET_ROLE_BY_ID = "SELECT * FROM role WHERE id = ?;";
    /**
     * Type table
     */

    public static final String GET_TYPE_BY_ID = "SELECT * FROM type WHERE id = ?;";
    /**
     * Level table
     */

    public static final String GET_LEVEL_BY_ID = "SELECT * FROM level WHERE id = ?;";
    /**
     * Answer table
     */

    public static final String GET_ANSWER_BY_ID = "SELECT * FROM answer WHERE id = ?;";
    public static final String GET_ANSWER_BY_QUESTION = "SELECT * FROM answer WHERE question = ?;";
    public static final String ADD_ANSWER = "INSERT INTO answer(question, answer, correctness) VALUES (?, ?, ?);";
    public static final String UPDATE_ANSWER = "UPDATE answer SET answer = ?, correctness = ? WHERE id = ?;";
    public static final String DELETE_ANSWER = "DELETE FROM answer WHERE id = ?;";
    public static final String DELETE_ANSWER_BY_QUESTION = "DELETE FROM answer WHERE question = ?;";
    /**
     * Block table
     */

    public static final String GET_BLOCK_BY_USER = "SELECT * FROM block WHERE student = ?;";
    public static final String BLOCK_USER = "INSERT INTO block(teacher, student, block) VALUES (?, ?, ?);";
    public static final String UNBLOCK_USER = "UPDATE block SET unblock = ? WHERE id = ?;";
    public static final String DELETE_BLOCK_BY_USER = "DELETE FROM block WHERE user = ?;";
    /**
     * Log table
     */

    public static final String GET_LOG_BY_USER = "SELECT * FROM log WHERE user = ?;";
    public static final String GET_LOG_BY_TEST = "SELECT * FROM log WHERE test = ?;";
    public static final String ADD_LOG = "INSERT INTO log(user, test, question, answer) VALUES (?, ?, ?, ?);";
    public static final String DELETE_LOG_BY_ANSWER = "DELETE FROM log WHERE answer = ?;";
    public static final String DELETE_LOG_BY_QUESTION = "DELETE FROM log WHERE question = ?;";
    public static final String DELETE_LOG_BY_USER = "DELETE FROM log WHERE user = ?;";
    public static final String GET_SUM_OF_POINTS = "SELECT SUM(answer.correctness) AS sum FROM answer JOIN log ON log.answer =answer.id WHERE test = ? AND user = ?;";
    public static final String GET_COUNT_OF_ANSWERS = "SELECT COUNT(*) AS count FROM answer JOIN question ON answer.question = question.id JOIN test ON question.test = test.id WHERE answer.correctness = 1 AND test = ?;";
    public static final String GET_COUNT_OF_ZEROS = "SELECT COUNT(answer.correctness) AS count FROM answer JOIN log ON log.answer =answer.id WHERE test = ? AND user = ? AND answer.correctness != 1;";
    /**
     * //QUESTION TABLE
     */

    public static final String GET_QUESTION_BY_ID = "SELECT * FROM question WHERE id = ?;";
    public static final String GET_QUESTION_BY_QUESTION = "SELECT * FROM question WHERE question = ? AND test = ? AND id != ?;";
    public static final String GET_QUESTION_BY_TEST = "SELECT * FROM question WHERE test = ?;";
    public static final String GET_LAST_QUESTION_BY_TEST = "SELECT * FROM question WHERE test = ? ORDER BY id DESC LIMIT 1;";
    public static final String ADD_QUESTION = "INSERT INTO question(test, question, type) VALUES (?, ?, ?);";
    public static final String UPDATE_QUESTION = "UPDATE question SET question = ?, type = ? WHERE id = ?;";
    public static final String DELETE_QUESTION = "DELETE FROM question WHERE id = ?;";
    /**
     * Result table
     */

    public static final String GET_RESULT_BY_TEST = "SELECT * FROM result WHERE test = ?;";
    public static final String GET_RESULT_BY_USER = "SELECT * FROM result WHERE user = ?;";
    public static final String GET_RESULT_BY_USER_AND_TEST = "SELECT * FROM result WHERE user = ? AND test = ?;";
    public static final String ADD_RESULT = "INSERT INTO result(user, test, date, result) VALUES (?, ?, ?, ?);";
    public static final String UPDATE_RESULT = "UPDATE result SET result = ? WHERE id = ?;";
    public static final String DELETE_RESULT = "DELETE FROM result WHERE id = ?;";
    public static final String DELETE_RESULT_BY_USER = "DELETE FROM question WHERE user = ?;";
    /**
     * Test table
     */

    public static final String GET_TEST_BY_ID = "SELECT * FROM test WHERE id = ?;";
    public static final String GET_TEST_BY_NAME_AND_OWNER = "SELECT * FROM test WHERE name = ? AND owner = ? AND id != ?;";
    public static final String ADD_TEST = "INSERT INTO test(owner, level, name, subject, created, is_close, time, finished) VALUES (?, ?, ?, ?, ?, ?, ?, 0);";
    public static final String UPDATE_TEST = "UPDATE test SET name = ?, subject = ?, is_close = ?, time = ?, level =? WHERE id = ?;";
    public static final String OPEN_TEST = "UPDATE test SET is_close = 1 WHERE id = ? AND is_close = 2;";
    public static final String ADD_POPULARITY = "UPDATE test SET finished = finished + 1 WHERE id = ?;";
    public static final String GET_LAST_ADDED_TEST_BY_OWNER = "SELECT * FROM test WHERE owner = ? ORDER BY id DESC LIMIT 1;";
    public static final String DELETE_TEST_BY_ID = "DELETE FROM test WHERE id = ?;";
    /**
     * User table
     */

    public static final String GET_USER_BY_ID = "SELECT * FROM user WHERE id = ?;";
    public static final String GET_USER_BY_EMAIL = "SELECT * FROM user WHERE email = ?;";
    public static final String ADD_USER = "INSERT INTO user(name, email, password, registration, role) VALUES (?, ?, ?, ?, ?);";
    public static final String UPDATE_USER = "UPDATE user SET name = ?, email = ?, password = ?, role = ? WHERE id = ?;";
    public static final String DELETE_USER = "DELETE FROM user WHERE id = ?;";
}
