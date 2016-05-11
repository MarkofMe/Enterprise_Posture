//package jollyroger.enterprise_posture;
//
//import android.content.Context;
//import android.content.Intent;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import java.util.Collections;
//import java.util.List;
//
//public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
//
//    private LayoutInflater inflater;
//    List<RecyclerButton> data = Collections.emptyList();
//    private Context context;
//
//    public RecyclerAdapter(Context context, List<RecyclerButton> data) //Context context
//    {
//        this.context = context;
//        inflater = LayoutInflater.from(context);
//        this.data = data;
//    }
//
//    @Override
//    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = inflater.inflate(R.layout.custom_row, parent, false);
//        Log.d("RecyclerAdapter", "onCreateViewHolder called");
//        MyViewHolder holder = new MyViewHolder(view);
//        return holder;
//    }
//
//    @Override
//    public void onBindViewHolder(MyViewHolder holder, int position) {
//        RecyclerButton current = data.get(position);
//        Log.d("RecyclerAdapter", "onBindViewHolder called " + position);
//        holder.listText.setText(current.getTitle());
//        holder.listIcon.setImageResource(current.getIconId());
//
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return data.size();
//    }
//
//    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//        TextView listText;
//        ImageView listIcon;
//
//        public MyViewHolder(View itemView) {
//            super(itemView);
//            listText = (TextView) itemView.findViewById(R.id.listText);
//            listIcon = (ImageView) itemView.findViewById(R.id.listIcon);
//            itemView.setOnClickListener(this);
//        }
//
//        @Override
//        public void onClick(View v) {
//
//            if (getPosition() == 0) {
//                context.startActivity(new Intent(context, CameraActivity.class));
//            } else if (getPosition() == 1) {
//                context.startActivity(new Intent(context, DatabaseActivity.class));
//            } else if (getPosition() == 2) {
//                context.startActivity(new Intent(context, SettingsActivity.class));
//            } else if (getPosition() == 3) {
//                context.startActivity(new Intent(context, CreditsActivity.class));
//            }
//        }
//    }
//}
