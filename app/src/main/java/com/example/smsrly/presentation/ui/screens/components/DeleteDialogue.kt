package com.example.smsrly.presentation.ui.screens.components

import androidx.compose.material3.AlertDialog

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.ui.graphics.Color

@Composable
fun DeleteDialogue(onConfirm: () -> Unit, onDismiss: () -> Unit,title:String) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Delete $title") },
        text = { Text("Are you sure you want to delete your $title? This action cannot be undone.") },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text("Delete", color = Color.Red)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )



}