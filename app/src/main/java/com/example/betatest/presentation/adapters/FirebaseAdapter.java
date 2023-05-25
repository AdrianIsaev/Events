package com.example.betatest.presentation.adapters;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.betatest.data.storage.room.entity.IventsSpot;
import com.example.betatest.R;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class FirebaseAdapter extends RecyclerView.Adapter<FirebaseAdapter.FirebaseViewHolder> {
    Context context;
    ArrayList<IventsSpot> list;
    //private SharedPreferences sharedPreferences;
    //public boolean isOn;
    public FirebaseAdapter(Context context, ArrayList<IventsSpot> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public FirebaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.project_item_firebase, parent, false);
        return  new FirebaseViewHolder(v, parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull FirebaseViewHolder holder, int position) {
        IventsSpot iventsSpot = list.get(position);



        TextView titleforagres, dateforagres;
        holder.titlefirebase.setText(iventsSpot.getTitleSpot());
        holder.watcherfirebase.setText(iventsSpot.getUsersSpot());
        holder.dateFirebase.setText(iventsSpot.getDateSpot());

        Picasso.get().load(iventsSpot.getImageSpot()).into(holder.imageView);

        if (iventsSpot.getTitleSpot().equals("Выступление Ленина")) {
            holder.imageView.setImageResource(R.drawable.lenin2);
        }
        if(iventsSpot.getTitleSpot().equals("Квест-шоу \"ИЛЛЮЗИЯ\"")){
            holder.imageView.setImageResource(R.drawable.kot228);
        }
        if (iventsSpot.getTitleSpot().equals("Фестиваль уличного искусства")){
            holder.imageView.setImageResource(R.drawable.treet2);
        }
        if (iventsSpot.getTitleSpot().equals("Историческая реконструкция битвы при Ватерлоо")){
            holder.imageView.setImageResource(R.drawable.ater);
        }
        NotificationChannel channel = new NotificationChannel("TEST_CHANNEL", "TEST DESCRIPTION", NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager notificationManager = holder.itemView.getContext().getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
        ImageView imgplus = holder.itemView.findViewById(R.id.imgplus);



        imgplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView dateforagres = holder.itemView.findViewById(R.id.dateFirebase);
                String lolik = dateforagres.getText().toString();
                TextView titleforagres= holder.itemView.findViewById(R.id.titleFirebase);
                String popik = titleforagres.getText().toString();

                Toast.makeText(holder.itemView.getContext(), "", Toast.LENGTH_LONG).show();
                Notification notification = new NotificationCompat.Builder(holder.itemView.getContext(), "TEST_CHANNEL")
                        .setContentTitle("Дорогой пользователь!")
                        .setContentText(lolik + " Приходи на " + popik + ", будет весело!")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setWhen(System.currentTimeMillis() +  60 * 1000) // Set the notification to be sent after 24 hours
                        .build();
                notificationManager.notify(42, notification);



                SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                try {
                    Date date = dateFormat.parse(lolik);
                    String lolipop = lolik + " Приходи на "+ popik +", будет весело!";
                    if (lolipop!=null) {
                        Calendar calendar = Calendar.getInstance();
                        //calendar.set(2023, 6, 16, 12, 0); // устанавливаем дату
                        calendar.setTime(date);
                        calendar.set(Calendar.HOUR_OF_DAY, 12);
                        calendar.set(Calendar.MINUTE, 0);
                        Intent intent = new Intent(Intent.ACTION_INSERT)
                                .setData(CalendarContract.Events.CONTENT_URI)
                                .putExtra(CalendarContract.Events.TITLE, lolipop)
                                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, calendar.getTimeInMillis()) // начало события
                                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, calendar.getTimeInMillis() + 60 * 60 * 1000); // конец события
//
                        context.startActivity(intent);
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class FirebaseViewHolder extends RecyclerView.ViewHolder{

        private Context context;
        TextView titlefirebase, watcherfirebase,dateFirebase, titleforagres, dateforagres;

        ImageView imageView;
        public FirebaseViewHolder(@NonNull View itemView, Context context){
            super(itemView);
            this.context = context;



            titlefirebase = itemView.findViewById(R.id.titleFirebase);
            watcherfirebase = itemView.findViewById(R.id.watcherFirebase);
            dateFirebase = itemView.findViewById(R.id.dateFirebase);
            imageView = itemView.findViewById(R.id.imgLogorifm);
        }
    }
}
