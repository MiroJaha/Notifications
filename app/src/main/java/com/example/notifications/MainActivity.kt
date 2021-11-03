package com.example.notifications

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {

    private val channelId = "myapp.notifications"
    private val description = "Notification App Example"
    private lateinit var notificationManager: NotificationManager
    private lateinit var builder: Notification.Builder
    private val notificationId =123
    private lateinit var notifyButton: Button
    private lateinit var messageEntry: EditText

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notifyButton= findViewById(R.id.button)
        messageEntry= findViewById(R.id.editTextTextPersonName)

        notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        notifyButton.setOnClickListener{
            if (messageEntry.text.isNotBlank()){
                builder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    val notificationChannel = NotificationChannel(channelId,description,NotificationManager.IMPORTANCE_HIGH)
                    notificationManager.createNotificationChannel(notificationChannel)
                    Notification.Builder(this, channelId)
                        .setSmallIcon(R.drawable.ic_notifications)
                        .setContentTitle("My Notification")
                        .setContentText(messageEntry.text)
                } else {
                    Notification.Builder(this)
                        .setSmallIcon(R.drawable.ic_notifications)
                        .setContentTitle("My Notification")
                        .setContentText(messageEntry.text)
                }
                notificationManager.notify(notificationId, builder.build())
            }
            else{
                Toast.makeText(this,"Please Enter Valid Values!!",Toast.LENGTH_SHORT).show()
            }

        }
    }
}