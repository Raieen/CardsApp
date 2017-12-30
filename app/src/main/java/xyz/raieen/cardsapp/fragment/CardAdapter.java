package xyz.raieen.cardsapp.fragment;

import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import xyz.raieen.cardsapp.Card;
import xyz.raieen.cardsapp.MainActivity;
import xyz.raieen.cardsapp.R;

/**
 * Created by Admin on 2017-12-29.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    List<Card> cardList;
    Context context;

    public CardAdapter(List<Card> cardList, Context context) {
        this.cardList = cardList;
        this.context = context;
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {

        TextView cardTextView;
        ImageView cardImageView;
        boolean flipped;

        public CardViewHolder(View itemView) {
            super(itemView);
            cardTextView = itemView.findViewById(R.id.cardTextView);
            cardImageView = itemView.findViewById(R.id.cardImageView);

        }
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);

        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CardViewHolder holder, int position) {

        final Card card = cardList.get(position);
        TextView cardTextView = holder.cardTextView;
        final ImageView cardImageView = holder.cardImageView;

        cardTextView.setText(card.getDisplayName());
        Glide.with(holder.itemView).load(card.getImageFront()).into(cardImageView);

        cardImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.flipped) {
                    Glide.with(holder.itemView).load(card.getImageFront()).into(cardImageView);
                } else {
                    Glide.with(holder.itemView).load(card.getImageBack()).into(cardImageView);
                }
                holder.flipped ^= true;
            }
        });

        cardImageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                MainActivity mainActivity = (MainActivity) context;
                mainActivity.getSupportFragmentManager().beginTransaction().add(0, new CardModifyDialogFragment()).addToBackStack(null).commit();

                // TODO: 2017-12-29 Open fragment_card_modify and finsih that.
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }
}
