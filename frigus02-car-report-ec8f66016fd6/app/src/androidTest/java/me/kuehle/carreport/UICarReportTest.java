package me.kuehle.carreport;

/**
 * Created by forerogo on 10/27/2017.
 */
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.app.Activity;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.filters.LargeTest;
import android.test.ActivityInstrumentationTestCase2;
import android.support.test.InstrumentationRegistry;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import me.kuehle.carreport.gui.FirstStartActivity;
/**
 * Basic tests showcasing simple view matchers and actions like {@link ViewMatchers#withId},
 * {@link ViewActions#click} and {@link ViewActions#typeText}.
 * <p>
 * Note that there is no need to tell Espresso that a view is in a different {@link Activity}.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class UICarReportTest {

    public static final String CarName = "Espresso";
    public static final String InitialMileage = "1000";
    public static final String ExpectedText = "Fuel consumption";
    /**
     * A JUnit {@link Rule @Rule} to launch your activity under test. This is a replacement
     * for {@link ActivityInstrumentationTestCase2}.
     * <p>
     * Rules are interceptors which are executed for each test method and will run before
     * any of your setup code in the {@link Before @Before} method.
     * <p>
     * {@link ActivityTestRule} will create and launch of the activity for you and also expose
     * the activity under test. To get a reference to the activity you can use
     * the {@link ActivityTestRule#getActivity()} method.
     */
    @Rule
    public ActivityTestRule<FirstStartActivity> mActivityRule = new ActivityTestRule<>(
            FirstStartActivity.class);

    @Test
    public void createNewCar_initialActivity() {
        onView(withId(R.id.btn_create_car)).perform(click());
        onView(withId(R.id.edt_name))
                .perform(typeText(CarName), closeSoftKeyboard());
        onView(withId(R.id.edt_initial_mileage))
                .perform(typeText(InitialMileage), closeSoftKeyboard());
        //openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        //onView(withText("MenuItemName")).perform(click());

        onView(withId(R.id.menu_save)).perform(click());
        //onView(withId(R.id.menu_save)).perform(click());

        // Check that the text was changed.
        onView(withId(R.id.txt_title)).check(matches(withText(ExpectedText)));
    }

}
