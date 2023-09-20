package es.upm.miw.iwvg_devops.rest;

import es.upm.miw.iwvg_devops.Fraction;
import es.upm.miw.iwvg_devops.User;
import es.upm.miw.iwvg_devops.UsersDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UsersDatabaseTest {
    private UsersDatabase db;

    @BeforeEach
    void initializeDatabase() {
        db = new UsersDatabase();
    }

    @Test
    void testUsersDatabaseFindAll() {
        boolean nonNull = db.findAll().noneMatch(Objects::isNull);
        List<User> usersList = db.findAll().toList();
        assertEquals(6, usersList.size());
        assertTrue(nonNull);
    }

    @Test
    void testUsersDatabaseFindFirstFractionByUserId() {
        final String firstUserId = "1";
        final String secondUserId = "2";
        Fraction expectedFirstUserProperFraction = new Fraction(0, 1);
        Fraction expectedSecondUserProperFraction = new Fraction(-1, 5);
        Fraction firstUserFraction = db.findFirstProperFractionByUserId(firstUserId);
        Fraction secondUserFraction = db.findFirstProperFractionByUserId(secondUserId);
        assertEquals(expectedFirstUserProperFraction.getNumerator(), firstUserFraction.getNumerator());
        assertEquals(expectedFirstUserProperFraction.getDenominator(), firstUserFraction.getDenominator());
        assertEquals(expectedSecondUserProperFraction.getNumerator(), secondUserFraction.getNumerator());
        assertEquals(expectedSecondUserProperFraction.getDenominator(), secondUserFraction.getDenominator());
    }

    @Test
    void testFindUserFamilyNameInitialBySomeProperFraction() {
        List<String> familyNameInitial = db.findUserFamilyNameInitialBySomeProperFraction().toList();
        assertEquals(4, familyNameInitial.size());
        assertEquals("F.", familyNameInitial.get(0));
        assertEquals("B.", familyNameInitial.get(1));
        assertEquals("L.", familyNameInitial.get(2));
        assertEquals("B.", familyNameInitial.get(3));
    }

    @Test
    void testFindUserFamilyNameBySomeImproperFraction() {
        List<String> familyNames = db.findUserFamilyNameBySomeImproperFraction().toList();
        assertEquals(6, familyNames.size());
        assertEquals("Fernandez", familyNames.get(0));
        assertEquals("Blanco", familyNames.get(1));
        assertEquals("López", familyNames.get(2));
        assertEquals("Torres", familyNames.get(3));
        assertEquals("Blanco", familyNames.get(4));
        assertEquals("Torres", familyNames.get(5));
    }
}
