package kg.geektech.android3lesson1_2.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.List;

import kg.geektech.android3lesson1_2.R;
import kg.geektech.android3lesson1_2.domain.Card;
import kg.geektech.android3lesson1_2.domain.Game;

public class EmojiGame {

    private Game<String> game;
    private final Context context;

    @RequiresApi(api = Build.VERSION_CODES.R)

    public EmojiGame(Context context) {
        this.context=context;
        game = new Game<>(List.of("❤", "🎃", "👹", "😎", "👽"));
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void choose(Card<String> card) {
        game.choose(card);
        if (game.isGameOver()){
            txtGameOver();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void txtGameOver() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(R.layout.alert_game_over_text).show();
    }

    public List<Card<String>> getCards() {
        return game.getCards();
    }
}
