package com.chutipon.reviewx;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import me.toptas.fancyshowcase.FancyShowCaseQueue;
import me.toptas.fancyshowcase.FancyShowCaseView;
import me.toptas.fancyshowcase.FocusShape;
import me.toptas.fancyshowcase.OnViewInflateListener;

/**
 * Created by Nicha Rojsrikul on 17/12/2560.
 */

public class Tutorial {
    private static Tutorial instance;

    public static Tutorial getInstance(){
        if(instance == null){
            instance = new Tutorial();
        }
        return instance;
    }

    public void showMenuTutorial(final Activity activity){
        final FancyShowCaseView showExplore = buildRoundRect(activity, activity.findViewById(R.id.tab_explore), "Explore is where you can look through your movie choices");
        final FancyShowCaseView showNear = buildRoundRect(activity, activity.findViewById(R.id.tab_nearby),"You can see near by cinemas here");
        final FancyShowCaseView showReview = buildRoundRect(activity, activity.findViewById(R.id.tab_myreview), "You can also see your past reviews");
        final FancyShowCaseView showLater = buildRoundRect(activity, activity.findViewById(R.id.tab_readLater), "You can see reviews you saved for later");
        final FancyShowCaseView showTutorial = buildRoundRect(activity, activity.findViewById(R.id.tab_tutorial), "Explore is where you can look through your movie choices");

        new FancyShowCaseQueue()
                .add(showExplore)
                .add(showNear)
                .add(showReview)
                .add(showLater)
                .add(showTutorial).show();
    }

    public void showReviewTutorial(final Activity activity){
        final FancyShowCaseView showScore = buildRoundRect(activity, activity.findViewById(R.id.score),"You give your score of the movie");
        final FancyShowCaseView showThree = buildRoundRect(activity, activity.findViewById(R.id.threeWords),"Here you give three words for the movie");
        final FancyShowCaseView showWrite = new FancyShowCaseView.Builder(activity)
                .focusOn(activity.findViewById(R.id.reviewText))
                .focusShape(FocusShape.ROUNDED_RECTANGLE)
                .roundRectRadius(20)
                .title("And here you write your own review")
                .titleGravity(Gravity.TOP)
                .showOnce("showWriteReviewDefinitely")
                .build();
        final FancyShowCaseView showButton = buildRoundRect(activity, activity.findViewById(R.id.btn_review),"When you finished, press here");

        new FancyShowCaseQueue().add(showScore)
                .add(showThree)
                .add(showWrite)
                .add(showButton)
                .show();
    }

    public FancyShowCaseView build(final Activity activity, View view, final String text){
        return new FancyShowCaseView.Builder(activity)
                .focusOn(view)
                .customView(R.layout.view_tutorial_custom, new OnViewInflateListener() {
                    @Override
                    public void onViewInflated(@NonNull View view) {
                        ((TextView)activity.findViewById(R.id.tutorialText)).setText(text);
                    }
                })
                .showOnce(""+view.getId())
                .build();
    }

    public FancyShowCaseView buildRoundRect(final Activity activity, View view, final String text){
        return new FancyShowCaseView.Builder(activity)
                .focusOn(view)
                .customView(R.layout.view_tutorial_custom, new OnViewInflateListener() {
                    @Override
                    public void onViewInflated(@NonNull View view) {
                        ((TextView)activity.findViewById(R.id.tutorialText)).setText(text);
                    }
                })
                .focusShape(FocusShape.ROUNDED_RECTANGLE)
                .roundRectRadius(20)
                .showOnce(""+view.getId())
                .build();
    }
}
