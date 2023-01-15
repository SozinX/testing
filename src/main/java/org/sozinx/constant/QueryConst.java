package org.sozinx.constant;

public class QueryConst {
    //ROLE TABLE
    public static final String GET_ROLE_BY_ID = "SELECT * FROM role WHERE id = ?;";
    //TYPE TABLE
    public static final String GET_TYPE_BY_ID = "SELECT * FROM type WHERE id = ?;";
    //LEVEL TABLE
    public static final String GET_LEVEL_BY_ID = "SELECT * FROM level WHERE id = ?;";
    //ANSWER TABLE
    public static final String GET_ANSWER_BY_ID = "SELECT * FROM answer WHERE id = ?;";
    public static final String GET_ANSWER_BY_QUESTION = "SELECT * FROM answer WHERE question = ?;";
    public static final String ADD_ANSWER = "INSERT INTO answer(question, answer, correctness) VALUES (?, ?, ?);";
    public static final String UPDATE_ANSWER = "UPDATE answer SET answer = ?, correctness = ? WHERE id = ?;";
    public static final String DELETE_ANSWER = "DELETE FROM answer WHERE id = ?;";
    public static final String DELETE_ANSWER_BY_QUESTION = "DELETE FROM answer WHERE question = ?;";
    //BLOCK TABLE
    public static final String GET_BLOCK_BY_USER = "SELECT * FROM block WHERE student = ?;";
    public static final String BLOCK_USER = "INSERT INTO block(teacher, student, block) VALUES (?, ?, ?);";
    public static final String UNBLOCK_USER = "UPDATE block SET unblock = ? WHERE student = ?; teacher = ?";
    public static final String DELETE_BLOCK_BY_USER = "DELETE FROM block WHERE user = ?;";
    //LOG TABLE
    public static final String GET_LOG_BY_USER = "SELECT * FROM log WHERE user = ?;";
    public static final String GET_LOG_BY_TEST = "SELECT * FROM log WHERE test = ?;";
    public static final String ADD_LOG = "INSERT INTO log(user, test, question, answer) VALUES (?, ?, ?, ?);";
    public static final String DELETE_LOG_BY_ANSWER = "DELETE FROM log WHERE answer = ?;";
    public static final String DELETE_LOG_BY_QUESTION = "DELETE FROM log WHERE question = ?;";
    public static final String DELETE_LOG_BY_USER = "DELETE FROM log WHERE user = ?;";
    //QUESTION TABLE
    public static final String GET_QUESTION_BY_ID = "SELECT * FROM question WHERE id = ?;";
    public static final String GET_QUESTION_BY_TEST = "SELECT * FROM question WHERE test = ?;";
    public static final String ADD_QUESTION = "INSERT INTO question(test, question, type) VALUES (?, ?, ?);";
    public static final String UPDATE_QUESTION = "UPDATE question SET question = ?, type = ? WHERE id = ?;";
    public static final String DELETE_QUESTION = "DELETE FROM question WHERE id = ?;";
    //RESULT TABLE
    public static final String GET_RESULT_BY_TEST = "SELECT * FROM result WHERE test = ?;";
    public static final String GET_RESULT_BY_USER = "SELECT * FROM result WHERE user = ?;";
    public static final String GET_RESULT_BY_USER_AND_TEST = "SELECT * FROM result WHERE user = ? AND test = ?;";
    public static final String ADD_RESULT = "INSERT INTO result(user, test, created, result) VALUES (?, ?, ?, ?);";
    public static final String UPDATE_RESULT = "UPDATE result SET result = ? WHERE id = ?;";
    public static final String DELETE_RESULT = "DELETE FROM result WHERE id = ?;";
    public static final String DELETE_RESULT_BY_USER = "DELETE FROM question WHERE user = ?;";
    //TEST TABLE
    public static final String GET_TEST_BY_ID = "SELECT * FROM test WHERE id = ?;";
    public static final String ADD_TEST = "INSERT INTO test(owner, level, name, subject, created, is_close, time, finished) VALUES (?, ?, ?, ?, ?, ?, ?, 0);";
    public static final String UPDATE_TEST = "UPDATE test SET name = ?, subject = ?, is_close = ?, time = ?, level =? WHERE id = ?;";
    public static final String ADD_POPULARITY = "UPDATE test SET finished = finished + 1 WHERE id = ?;";
   // public static final String GET_TEST_BY_USER_ASC = "SELECT * FROM test WHERE owner = ? ORDER BY name ASC;";
    public static final String GET_TEST_BY_NAME_ASC = "SELECT * FROM test ORDER BY name ASC;";
    public static final String GET_TEST_BY_NAME_DESC = "SELECT * FROM test ORDER BY name DESC;";
    public static final String GET_TEST_BY_SUBJECT_ASC = "SELECT * FROM test ORDER BY subject ASC;";
    public static final String GET_TEST_BY_SUBJECT_DESC = "SELECT * FROM test ORDER BY subject DESC;";
    public static final String GET_TEST_BY_POPULARITY_ASC = "SELECT * FROM test ORDER BY finished ASC;";
    public static final String GET_TEST_BY_POPULARITY_DESC = "SELECT * FROM test ORDER BY finished DESC;";
    public static final String GET_TEST_BY_DATE_ASC = "SELECT * FROM test ORDER BY created ASC;";
    public static final String GET_TEST_BY_DATE_DESC = "SELECT * FROM test ORDER BY created DESC;";
    public static final String GET_TEST_BY_LEVEL_ASC = "SELECT * FROM test ORDER BY level ASC;";
    public static final String GET_TEST_BY_LEVEL_DESC = "SELECT * FROM test ORDER BY level DESC;";
    public static final String GET_TEST_BY_ELECT_NAME_ORDER_BY_NAME_ASC = "SELECT * FROM test WHERE name LIKE ? ORDER BY name ASC;";
    public static final String GET_TEST_BY_ELECT_NAME_ORDER_BY_NAME_DESC = "SELECT * FROM test WHERE name LIKE ? ORDER BY name DESC;";
    public static final String GET_TEST_BY_ELECT_NAME_ORDER_BY_SUBJECT_ASC = "SELECT * FROM test WHERE name LIKE ? ORDER BY subject ASC;";
    public static final String GET_TEST_BY_ELECT_NAME_ORDER_BY_SUBJECT_DESC = "SELECT * FROM test WHERE name LIKE ? ORDER BY subject DESC;";
    public static final String GET_TEST_BY_ELECT_NAME_ORDER_BY_POPULARITY_ASC = "SELECT * FROM test WHERE name LIKE ? ORDER BY finished ASC;";
    public static final String GET_TEST_BY_ELECT_NAME_ORDER_BY_POPULARITY_DESC = "SELECT * FROM test WHERE name LIKE ? ORDER BY finished DESC;";
    public static final String GET_TEST_BY_ELECT_NAME_ORDER_BY_DATE_ASC = "SELECT * FROM test WHERE name LIKE ? ORDER BY created ASC;";
    public static final String GET_TEST_BY_ELECT_NAME_ORDER_BY_DATE_DESC = "SELECT * FROM test WHERE name LIKE ? ORDER BY created DESC;";
    public static final String GET_TEST_BY_ELECT_NAME_ORDER_BY_LEVEL_ASC = "SELECT * FROM test WHERE name LIKE ? ORDER BY level ASC;";
    public static final String GET_TEST_BY_ELECT_NAME_ORDER_BY_LEVEL_DESC = "SELECT * FROM test WHERE name LIKE ? ORDER BY level DESC;";
    public static final String GET_TEST_BY_ELECT_SUBJECT_ORDER_BY_NAME_ASC = "SELECT * FROM test WHERE subject LIKE ? ORDER BY name ASC;";
    public static final String GET_TEST_BY_ELECT_SUBJECT_ORDER_BY_NAME_DESC = "SELECT * FROM test WHERE subject LIKE ? ORDER BY name DESC;";
    public static final String GET_TEST_BY_ELECT_SUBJECT_ORDER_BY_SUBJECT_ASC = "SELECT * FROM test WHERE subject LIKE ? ORDER BY subject ASC;";
    public static final String GET_TEST_BY_ELECT_SUBJECT_ORDER_BY_SUBJECT_DESC = "SELECT * FROM test WHERE subject LIKE ? ORDER BY subject DESC;";
    public static final String GET_TEST_BY_ELECT_SUBJECT_ORDER_BY_POPULARITY_ASC = "SELECT * FROM test WHERE subject LIKE ? ORDER BY finished ASC;";
    public static final String GET_TEST_BY_ELECT_SUBJECT_ORDER_BY_POPULARITY_DESC = "SELECT * FROM test WHERE subject LIKE ? ORDER BY finished DESC;";
    public static final String GET_TEST_BY_ELECT_SUBJECT_ORDER_BY_DATE_ASC = "SELECT * FROM test WHERE subject LIKE ? ORDER BY created ASC;";
    public static final String GET_TEST_BY_ELECT_SUBJECT_ORDER_BY_DATE_DESC = "SELECT * FROM test WHERE subject LIKE ? ORDER BY created DESC;";
    public static final String GET_TEST_BY_ELECT_SUBJECT_ORDER_BY_LEVEL_ASC = "SELECT * FROM test WHERE subject LIKE ? ORDER BY level ASC;";
    public static final String GET_TEST_BY_ELECT_SUBJECT_ORDER_BY_LEVEL_DESC = "SELECT * FROM test WHERE subject LIKE ? ORDER BY level DESC;";
    public static final String GET_TEST_BY_ELECT_LEVEL_ORDER_BY_NAME_ASC = "SELECT * FROM test WHERE level = ? ORDER BY name ASC;";
    public static final String GET_TEST_BY_ELECT_LEVEL_ORDER_BY_NAME_DESC = "SELECT * FROM test WHERE level = ? ORDER BY name DESC;";
    public static final String GET_TEST_BY_ELECT_LEVEL_ORDER_BY_SUBJECT_ASC = "SELECT * FROM test WHERE level = ? ORDER BY subject ASC;";
    public static final String GET_TEST_BY_ELECT_LEVEL_ORDER_BY_SUBJECT_DESC = "SELECT * FROM test WHERE level = ? ORDER BY subject DESC;";
    public static final String GET_TEST_BY_ELECT_LEVEL_ORDER_BY_POPULARITY_ASC = "SELECT * FROM test WHERE level = ? ORDER BY finished ASC;";
    public static final String GET_TEST_BY_ELECT_LEVEL_ORDER_BY_POPULARITY_DESC = "SELECT * FROM test WHERE level = ? ORDER BY finished DESC;";
    public static final String GET_TEST_BY_ELECT_LEVEL_ORDER_BY_DATE_ASC = "SELECT * FROM test WHERE level = ? ORDER BY created ASC;";
    public static final String GET_TEST_BY_ELECT_LEVEL_ORDER_BY_DATE_DESC = "SELECT * FROM test WHERE level = ? ORDER BY created DESC;";
    public static final String GET_TEST_BY_ELECT_NAME_AND_SUBJECT_ORDER_BY_NAME_ASC = "SELECT * FROM test WHERE name LIKE ? AND subject LIKE ? ORDER BY name ASC;";
    public static final String GET_TEST_BY_ELECT_NAME_AND_SUBJECT_ORDER_BY_NAME_DESC = "SELECT * FROM test WHERE name LIKE ? AND subject LIKE ? ORDER BY name DESC;";
    public static final String GET_TEST_BY_ELECT_NAME_AND_SUBJECT_ORDER_BY_SUBJECT_ASC = "SELECT * FROM test WHERE name LIKE ? AND subject LIKE ? ORDER BY subject ASC;";
    public static final String GET_TEST_BY_ELECT_NAME_AND_SUBJECT_ORDER_BY_SUBJECT_DESC = "SELECT * FROM test WHERE name LIKE ? AND subject LIKE ? ORDER BY subject DESC;";
    public static final String GET_TEST_BY_ELECT_NAME_AND_SUBJECT_ORDER_BY_POPULARITY_ASC = "SELECT * FROM test WHERE name LIKE ? AND subject LIKE ? ORDER BY finished ASC;";
    public static final String GET_TEST_BY_ELECT_NAME_AND_SUBJECT_ORDER_BY_POPULARITY_DESC = "SELECT * FROM test WHERE name LIKE ? AND subject LIKE ? ORDER BY finished DESC;";
    public static final String GET_TEST_BY_ELECT_NAME_AND_SUBJECT_ORDER_BY_DATE_ASC = "SELECT * FROM test WHERE name LIKE ? AND subject LIKE ? ORDER BY created ASC;";
    public static final String GET_TEST_BY_ELECT_NAME_AND_SUBJECT_ORDER_BY_DATE_DESC = "SELECT * FROM test WHERE name LIKE ? AND subject LIKE ? ORDER BY created DESC;";
    public static final String GET_TEST_BY_ELECT_NAME_AND_SUBJECT_ORDER_BY_LEVEL_ASC = "SELECT * FROM test WHERE name LIKE ? AND subject LIKE ? ORDER BY level ASC;";
    public static final String GET_TEST_BY_ELECT_NAME_AND_SUBJECT_ORDER_BY_LEVEL_DESC = "SELECT * FROM test WHERE name LIKE ? AND subject LIKE ? ORDER BY level DESC;";
    public static final String GET_TEST_BY_ELECT_NAME_AND_LEVEL_ORDER_BY_NAME_ASC = "SELECT * FROM test WHERE name LIKE ? AND level = ? ORDER BY name ASC;";
    public static final String GET_TEST_BY_ELECT_NAME_AND_LEVEL_ORDER_BY_NAME_DESC = "SELECT * FROM test WHERE name LIKE ? AND level = ? ORDER BY name DESC;";
    public static final String GET_TEST_BY_ELECT_NAME_AND_LEVEL_ORDER_BY_SUBJECT_ASC = "SELECT * FROM test WHERE name LIKE ? AND level = ? ORDER BY subject ASC;";
    public static final String GET_TEST_BY_ELECT_NAME_AND_LEVEL_ORDER_BY_SUBJECT_DESC = "SELECT * FROM test WHERE name LIKE ? AND level = ? ORDER BY subject DESC;";
    public static final String GET_TEST_BY_ELECT_NAME_AND_LEVEL_ORDER_BY_POPULARITY_ASC = "SELECT * FROM test WHERE name LIKE ? AND level = ? ORDER BY finished ASC;";
    public static final String GET_TEST_BY_ELECT_NAME_AND_LEVEL_ORDER_BY_POPULARITY_DESC = "SELECT * FROM test WHERE name LIKE ? AND level = ? ORDER BY finished DESC;";
    public static final String GET_TEST_BY_ELECT_NAME_AND_LEVEL_ORDER_BY_DATE_ASC = "SELECT * FROM test WHERE name LIKE ? AND level = ? ORDER BY created ASC;";
    public static final String GET_TEST_BY_ELECT_NAME_AND_LEVEL_ORDER_BY_DATE_DESC = "SELECT * FROM test WHERE name LIKE ? AND level = ? ORDER BY created DESC;";
    public static final String GET_TEST_BY_ELECT_SUBJECT_AND_LEVEL_ORDER_BY_NAME_ASC = "SELECT * FROM test WHERE subject LIKE ? AND level = ? ORDER BY name ASC;";
    public static final String GET_TEST_BY_ELECT_SUBJECT_AND_LEVEL_ORDER_BY_NAME_DESC = "SELECT * FROM test WHERE subject LIKE ? AND level = ? ORDER BY name DESC;";
    public static final String GET_TEST_BY_ELECT_SUBJECT_AND_LEVEL_ORDER_BY_SUBJECT_ASC = "SELECT * FROM test WHERE subject LIKE ? AND level = ? ORDER BY subject ASC;";
    public static final String GET_TEST_BY_ELECT_SUBJECT_AND_LEVEL_ORDER_BY_SUBJECT_DESC = "SELECT * FROM test WHERE subject LIKE ? AND level = ? ORDER BY subject DESC;";
    public static final String GET_TEST_BY_ELECT_SUBJECT_AND_LEVEL_ORDER_BY_POPULARITY_ASC = "SELECT * FROM test WHERE subject LIKE ? AND level = ? ORDER BY finished ASC;";
    public static final String GET_TEST_BY_ELECT_SUBJECT_AND_LEVEL_ORDER_BY_POPULARITY_DESC = "SELECT * FROM test WHERE subject LIKE ? AND level = ? ORDER BY finished DESC;";
    public static final String GET_TEST_BY_ELECT_SUBJECT_AND_LEVEL_ORDER_BY_DATE_ASC = "SELECT * FROM test WHERE subject LIKE ? AND level = ? ORDER BY created ASC;";
    public static final String GET_TEST_BY_ELECT_SUBJECT_AND_LEVEL_ORDER_BY_DATE_DESC = "SELECT * FROM test WHERE subject LIKE ? AND level = ? ORDER BY created DESC;";
    public static final String GET_TEST_BY_ELECT_NAME_AND_SUBJECT_AND_LEVEL_ORDER_BY_NAME_ASC = "SELECT * FROM test WHERE name LIKE ? AND subject LIKE ? AND level = ? ORDER BY name ASC;";
    public static final String GET_TEST_BY_ELECT_NAME_AND_SUBJECT_AND_LEVEL_ORDER_BY_NAME_DESC = "SELECT * FROM test WHERE name LIKE ? AND subject LIKE ? AND level = ? ORDER BY name DESC;";
    public static final String GET_TEST_BY_ELECT_NAME_AND_SUBJECT_AND_LEVEL_ORDER_BY_SUBJECT_ASC = "SELECT * FROM test WHERE name LIKE ? AND subject LIKE ? AND level = ? ORDER BY subject ASC;";
    public static final String GET_TEST_BY_ELECT_NAME_AND_SUBJECT_AND_LEVEL_ORDER_BY_SUBJECT_DESC = "SELECT * FROM test WHERE name LIKE ? AND subject LIKE ? AND level = ? ORDER BY subject DESC;";
    public static final String GET_TEST_BY_ELECT_NAME_AND_SUBJECT_AND_LEVEL_ORDER_BY_POPULARITY_ASC = "SELECT * FROM test WHERE name LIKE ? AND subject LIKE ? AND level = ? ORDER BY finished ASC;";
    public static final String GET_TEST_BY_ELECT_NAME_AND_SUBJECT_AND_LEVEL_ORDER_BY_POPULARITY_DESC = "SELECT * FROM test WHERE name LIKE ? AND subject LIKE ? AND level = ? ORDER BY finished DESC;";
    public static final String GET_TEST_BY_ELECT_NAME_AND_SUBJECT_AND_LEVEL_ORDER_BY_DATE_ASC = "SELECT * FROM test WHERE name LIKE ? AND subject LIKE ? AND level = ? ORDER BY created ASC;";
    public static final String GET_TEST_BY_ELECT_NAME_AND_SUBJECT_AND_LEVEL_ORDER_BY_DATE_DESC = "SELECT * FROM test WHERE name LIKE ? AND subject LIKE ? AND level = ? ORDER BY created DESC;";
    //USER TABLE
    public static final String GET_USER_BY_ID = "SELECT * FROM user WHERE id = ?;";
    public static final String GET_USER_BY_EMAIL = "SELECT * FROM user WHERE email = ?;";
    public static final String ADD_USER = "INSERT INTO user(name, email, password, registration, role) VALUES (?, ?, ?, ?, ?);";
    public static final String UPDATE_USER = "UPDATE user SET name = ?, email = ?, password = ?, role = ? WHERE id = ?;";
    public static final String DELETE_USER = "DELETE FROM user WHERE id = ?;";
}
