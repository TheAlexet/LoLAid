package adapters;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lolaid.R;

import java.util.List;

import business_logic.data_models.MatchDto;

public class MatchHistoryAdapter extends RecyclerView.Adapter<MatchHistoryAdapter.ViewHolder>{
    private List<MatchInfo> matchesList;

    public MatchHistoryAdapter(List<MatchInfo> list){
        matchesList = list;
    }

    @NonNull
    @Override
    public MatchHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.match_history_row, parent, false);
        MatchHistoryAdapter.ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() { return matchesList.size(); }

    @Override
    public void onBindViewHolder(@NonNull MatchHistoryAdapter.ViewHolder holder, int position) {
        //holder.tvDuration.setText();
        //holder.tvScore.setText();
        //holder.imChampion.setImageResource();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvScore;
        public TextView tvDuration;
        public ImageView imChampion;

        public ViewHolder(View view) {
            super(view);
            tvScore = (TextView) view.findViewById(R.id.tScore);
            tvDuration = (TextView) view.findViewById(R.id.tDuration);
            imChampion = (ImageView) view.findViewById(R.id.iChampion);

            view.setOnClickListener((v) -> {
                itemClick.onItemClickListener(getAdapterPosition());
            });
        }

        public interface OnItemClickListener {
            void onItemClickListener(int position);
        }
    }
}
