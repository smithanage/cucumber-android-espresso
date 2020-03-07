package com.example.avjindersinghsekhon.minimaltodo.test;

import android.app.Activity;
import android.content.Intent;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.test.InstrumentationTestCase;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.example.avjindersinghsekhon.minimaltodo.About.AboutActivity;
import com.example.avjindersinghsekhon.minimaltodo.Main.MainActivity;
import com.example.avjindersinghsekhon.minimaltodo.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.core.IsInstanceOf;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

public class createTodoSteps {
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class, false, true);
    private Activity activity;

    @Before
    public void setup() {
        activityTestRule.launchActivity(new Intent(Intent.ACTION_MAIN));
        activity = activityTestRule.getActivity();
    }

    @After
    public void tearDown() {
        activityTestRule.finishActivity();
    }

    @Given("^I am in the listing page$")
    public void iAmInTheListingPage() {
        assertNotNull(activity);
        ViewInteraction imageButton = onView(
                allOf(withId(R.id.addToDoItemFAB),
                        childAtPosition(
                                allOf(withId(R.id.myCoordinatorLayout),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                0)),
                                1),
                        isDisplayed()));
    }

    @When("^I add a Todo$")
    public void iAddATodo() {
         ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.addToDoItemFAB),
                        childAtPosition(
                                allOf(withId(R.id.myCoordinatorLayout),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                2),
                        isDisplayed()));
        floatingActionButton.perform(click());
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.userToDoEditText),
                        childAtPosition(
                                allOf(withId(R.id.toDoCustomTextInput),
                                        childAtPosition(
                                                withId(R.id.editTextParentLinearLayout),
                                                0)),
                                0),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("Todo 1"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.userToDoDescription),
                        childAtPosition(
                                allOf(withId(R.id.toDoCustomTextInput01),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                0),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("Desc 1"), closeSoftKeyboard());

        ViewInteraction floatingActionButton2 = onView(
                allOf(withId(R.id.makeToDoFloatingActionButton),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                1),
                        isDisplayed()));
        floatingActionButton2.perform(click());
    }

    @Then("^I should see the Todo listed$")
    public void iShouldSeeTheTodoListed(){
        ViewInteraction textView = onView(
                allOf(withId(R.id.toDoListItemTextview), withText("Todo 1"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.listItemLinearLayout),
                                        1),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Todo 1")));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
