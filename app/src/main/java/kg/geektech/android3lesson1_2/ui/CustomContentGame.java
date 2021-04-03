package kg.geektech.android3lesson1_2.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

import kg.geektech.android3lesson1_2.R;
import kg.geektech.android3lesson1_2.domain.Card;
import kg.geektech.android3lesson1_2.domain.CustomContent;
import kg.geektech.android3lesson1_2.domain.Game;

public class CustomContentGame {

    private final Game<CustomContent> game;
    private final Context context;

    @RequiresApi(api = Build.VERSION_CODES.R)
    public CustomContentGame(Context context) {
        this.context=context;
        game = new Game<>(List.of(
                new CustomContent(1,"Card 1", 15.0),
                new CustomContent(2,"Card 2", 13.0),
                new CustomContent(3,"Card 3", 13.0)
        ));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void choose (Card<CustomContent> card){
        game.choose(card);
        if (game.isGameOver()) {
            txtGameOver();
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void txtGameOver() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(R.layout.alert_game_over_text).show();
    }

    public List<Card<CustomContent>> getCards(){
        return game.getCards();
    }
}
