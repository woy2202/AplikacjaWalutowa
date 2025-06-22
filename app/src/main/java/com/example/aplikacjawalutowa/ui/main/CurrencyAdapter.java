package com.example.aplikacjawalutowa.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.example.aplikacjawalutowa.R;
import com.example.aplikacjawalutowa.data.local.CurrencyEntity;

public class CurrencyAdapter extends ListAdapter<CurrencyEntity, CurrencyAdapter.CurrencyViewHolder> {

    private OnItemClickListener listener;

    public CurrencyAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<CurrencyEntity> DIFF_CALLBACK = new DiffUtil.ItemCallback<CurrencyEntity>() {
        @Override
        public boolean areItemsTheSame(@NonNull CurrencyEntity oldItem, @NonNull CurrencyEntity newItem) {
            return oldItem.code.equals(newItem.code);
        }

        @Override
        public boolean areContentsTheSame(@NonNull CurrencyEntity oldItem, @NonNull CurrencyEntity newItem) {
            return oldItem.currencyName.equals(newItem.currencyName) && oldItem.rate == newItem.rate;
        }
    };

    @NonNull
    @Override
    public CurrencyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_currency, parent, false);
        return new CurrencyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyViewHolder holder, int position) {
        CurrencyEntity currentCurrency = getItem(position);
        holder.textViewCode.setText(currentCurrency.code);
        holder.textViewName.setText(currentCurrency.currencyName);
        holder.textViewRate.setText(String.format("%.4f", currentCurrency.rate));
    }

    class CurrencyViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewCode;
        private final TextView textViewName;
        private final TextView textViewRate;

        public CurrencyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCode = itemView.findViewById(R.id.text_view_currency_code);
            textViewName = itemView.findViewById(R.id.text_view_currency_name);
            textViewRate = itemView.findViewById(R.id.text_view_currency_rate);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(getItem(position));
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(CurrencyEntity currency);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}