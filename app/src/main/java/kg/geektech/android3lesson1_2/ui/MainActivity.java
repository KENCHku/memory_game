package kg.geektech.android3lesson1_2.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import kg.geektech.android3lesson1_2.R;
import kg.geektech.android3lesson1_2.domain.Card;
import kg.geektech.android3lesson1_2.domain.CustomContent;
import kg.geektech.android3lesson1_2.ui.adapters.EmojiAdapter;

public class MainActivity extends AppCompatActivity implements  EmojiAdapter.Listener {

    private RecyclerView recyclerView;
    private EmojiGame emojiGame;
    private EmojiAdapter emojiAdapter;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emojiGame = new EmojiGame(this);
        emojiAdapter = new EmojiAdapter(emojiGame,this );
        recyclerView =findViewById(R.id.rv_cards);
        recyclerView.setAdapter(emojiAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);

      //CustomContent customContent =  new CustomContent(1,"Card 1", 15.0);
      //CustomContent customContent2 =  new CustomContent(1,"Card 1", 15.0);
     //Log.d("tag1", String.valueOf(customContent.equals(customContent2)));


    }


    @Override
    public void choose(Card<String> card) {
        emojiGame.choose(card);
        Log.e("tag", "choose: "+ card.getId());
        emojiAdapter.notifyDataSetChanged();
    }
}