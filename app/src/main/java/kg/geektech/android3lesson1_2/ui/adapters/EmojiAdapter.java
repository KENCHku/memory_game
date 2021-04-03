package kg.geektech.android3lesson1_2.ui.adapters;

import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import kg.geektech.android3lesson1_2.R;
import kg.geektech.android3lesson1_2.domain.Card;
import kg.geektech.android3lesson1_2.ui.EmojiGame;

@RequiresApi(api = Build.VERSION_CODES.R)

public class EmojiAdapter extends RecyclerView.Adapter<EmojiAdapter.CustomContentHolder> {

    private final EmojiGame emojiGame ;
    private final Listener listener;

    public EmojiAdapter(EmojiGame emojiGame, Listener listener) {
        this.emojiGame = emojiGame;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CustomContentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new CustomContentHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomContentHolder holder, int position) {
        holder.onBind(emojiGame.getCards().get(position));
    }

    @Override
    public int getItemCount() {
        return emojiGame.getCards().size();
    }

    class CustomContentHolder extends RecyclerView.ViewHolder {

        private final TextView tvCard;

        public CustomContentHolder(@NonNull View itemView) {
            super(itemView);
            tvCard = itemView.findViewById(R.id.tv_card);
        }

        public void onBind(Card<String> card) {
            if (card.isFaceUp()) {
                tvCard.setText(card.getContent());
                tvCard.setBackgroundColor(Color.WHITE);
            } else {
                tvCard.setText("");
                tvCard.setBackgroundColor(Color.BLUE);
            }
            itemView.setOnClickListener(v -> {
                emojiGame.choose(card);
                listener.choose();
            });
        }
    }

    public interface Listener {
        void choose();
    }
}
