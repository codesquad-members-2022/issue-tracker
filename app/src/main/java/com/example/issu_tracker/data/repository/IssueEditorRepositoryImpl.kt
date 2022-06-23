package com.example.issu_tracker.data.repository

import android.util.Log
import androidx.core.net.toUri
import com.example.issu_tracker.data.Label
import com.example.issu_tracker.data.User
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class IssueEditorRepositoryImpl @Inject constructor(private val fireStore: FirebaseFirestore) :
    IssueEditorRepository {

    override suspend fun loadLabel(): List<String> {
        val labelList = mutableListOf<String>()
        val collectionData = fireStore.collection("Label").get().await()

        collectionData.documents.forEach { it ->
            val labelObj = it.toObject(Label::class.java)
            labelObj?.let { label ->
                labelList.add(label.content)
            }
        }
        return labelList
    }

    override suspend fun loadAssignee(): List<String> {
        val assigneeList = mutableListOf<String>()
        val collectionData = fireStore.collection("User").get().await()

        collectionData.documents.forEach {
            val assigneeObj = it.toObject(User::class.java)
            assigneeObj?.let { user ->
                assigneeList.add(user.name)
            }
        }
        return assigneeList
    }

    override suspend fun uploadImageAndGetImageUriFromFireBase(imageUriFromLocal: String): String {
        var imageUrlFromFirebase = ""
        val imageUriFromLocal = imageUriFromLocal.toUri()
        val storage = FirebaseStorage.getInstance()
        val storageRef = storage.reference.child("images/${imageUriFromLocal.lastPathSegment}")
        val uploadTask = storageRef.putFile(imageUriFromLocal)

        uploadTask.addOnCanceledListener {
            Log.e("uploadError", "이미지 업로드 에러")
        }.addOnSuccessListener { taskSnapshot ->
            imageUrlFromFirebase = convertUriToImageUrl(taskSnapshot)
        }.await()

        return imageUrlFromFirebase
    }

    private fun convertUriToImageUrl(taskSnapshot: UploadTask.TaskSnapshot): String {
        val convertedUrl = taskSnapshot.uploadSessionUri.toString()
            .replace(OLD_STRING_FOR_IMAGE_URL_CONVERSION, NEW_STRING_FOR_IMAGE_URL_CONVERSION)
        val imageUriInMarkDown = "![](${
            convertedUrl.substring(
                0,
                convertedUrl.indexOf(SUBSTRING_FOR_IMAGE_URL_CONVERSION)
            ) + MEDIA_FORMAT
        })"
        return imageUriInMarkDown
    }

    companion object {
        private const val OLD_STRING_FOR_IMAGE_URL_CONVERSION = "?name="
        private const val NEW_STRING_FOR_IMAGE_URL_CONVERSION = "/"
        private const val SUBSTRING_FOR_IMAGE_URL_CONVERSION = "&uploadType"
        private const val MEDIA_FORMAT = "?alt=media"
    }

}