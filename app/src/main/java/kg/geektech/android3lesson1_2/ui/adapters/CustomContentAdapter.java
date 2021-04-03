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
import kg.geektech.android3lesson1_2.domain.CustomContent;
import kg.geektech.android3lesson1_2.ui.CustomContentGame;
import kg.geektech.android3lesson1_2.ui.EmojiGame;

@RequiresApi(api = Build.VERSION_CODES.R)
public class CustomContentAdapter extends RecyclerView.Adapter<CustomContentAdapter.CustomContentHolder> {

    private final CustomContentGame customContentGame;
    private final Listener listener;


    public CustomContentAdapter(Listener listener,CustomContentGame customContentGame) {
        this.listener=listener;
        this.customContentGame=customContentGame;
    }

    @NonNull
    @Override
    public CustomContentAdapter.CustomContentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new CustomContentAdapter.CustomContentHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomContentAdapter.CustomContentHolder holder, int position) {
        holder.onBind(customContentGame.getCards().get(position));
    }

    @Override
    public int getItemCount() {
        return customContentGame.getCards().size();
    }

    class CustomContentHolder extends RecyclerView.ViewHolder {

        private final TextView tvCard;

        public CustomContentHolder(@NonNull View itemView) {
            super(itemView);
            tvCard = itemView.findViewById(R.id.tv_card);
        }

        public void onBind(Card<CustomContent> card) {
            if (card.isFaceUp()) {
                tvCard.setText(card.getContent().getName());
                tvCard.setBackgroundColor(Color.WHITE);
            } else {
                tvCard.setText("");
                tvCard.setBackgroundColor(Color.BLUE);
            }
            itemView.setOnClickListener(v -> {
                customContentGame.choose(card);
                listener.choose();
            });
        }
    }

    public interface Listener {
        void choose();
    }
}

