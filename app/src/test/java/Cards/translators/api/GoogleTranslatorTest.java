package Cards.translators.api;
/*
   Date: 11/27/2020
   Test Cases for Google Translator
 */

import com.google.api.client.util.DateTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.security.GeneralSecurityException;

import static Cards.translators.api.GoogleTranslatorTest.testData.*;
import static org.junit.jupiter.api.Assertions.*;

class GoogleTranslatorTest {

    private final GoogleTranslator googleTranslator = new GoogleTranslator();

    /**
     * Test case data
     */
    enum testData {
        TITLE {
            @Override
            public String toString() {
                return "Testing";
            }
        },
        BEGIN_DATE {
            @Override
            public DateTime getValue() {
                // TODO Add Date
                return null;
            }
        },
        END_DATE {
            @Override
            public DateTime getValue() {
                // TODO Add date
                return null;
            }
        },
        DESCRIPTION {
            @Override
            public String toString() {
                // TODO Add text
                return null;
            }
        },
        CREATED_DATE {
            @Override
            public DateTime getValue() {
                // TODO Add Date
                return null;
            }
        },
        EVENT_ID {
            @Override
            public String toString() {
                // TODO Add ID
                return null;
            }
        },
        ALL_DAY {
            @Override
            public Object getValue() {
                // TODO add boolean
                return null;
            }
        };

//=================  GETTERS ===============
        public Object getValue() {
            return null;
        }
        // TODO Finish
    }

    /**
     * Test case involving creating a new event.
     */
    @Test
    void newTaskTest() {
        // Create Task
        TaskEvent task;
        try {
            task = new TaskEvent(TITLE.toString(), (DateTime) BEGIN_DATE.getValue(),
                    (DateTime) END_DATE.getValue(), DESCRIPTION.toString(), (DateTime) CREATED_DATE.getValue(), null, EVENT_ID.toString(), (Boolean) ALL_DAY.getValue());
        } catch (GeneralSecurityException | IOException _e) {
            _e.printStackTrace();
        }
        // TODO Code For implementing task

        // Change this for bad test case
        if (true) {
            // Test  Case Failed
            fail("Insert Message");
        }
        if (true) {
            // Test Case Failed
            fail("Insert Message");
        }
        // Otherwise the test passes
    }

}