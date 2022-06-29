package com.example.issu_tracker.data.repository

import android.util.Log
import androidx.core.net.toUri
import com.example.issu_tracker.data.Issue
import com.example.issu_tracker.data.Label
import com.example.issu_tracker.data.User
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class IssueEditorRepositoryImpl @Inject constructor(private val fireStore: FirebaseFirestore) :
    IssueEditorRepository {

    override suspend fun loadLabel(): Map<String, Label> {
        val labelList = mutableMapOf<String, Label>()
        val collectionData = fireStore.collection(FIREBASE_COLLECTION_LABEL_PATH).get().await()

        collectionData.documents.forEach { it ->
            val labelObj = it.toObject(Label::class.java)
            labelObj?.let { label ->
                labelList[label.content] = label
            }
        }
        return labelList
    }

    override suspend fun loadAssignee(): Map<String, User> {
        val assigneeList = mutableMapOf<String, User>()
        val collectionData = fireStore.collection(FIREBASE_COLLECTION_USER_PATH).get().await()

        collectionData.documents.forEach {
            val assigneeObj = it.toObject(User::class.java)
            assigneeObj?.let { user ->
                assigneeList[user.name] = user
            }
        }
        return assigneeList
    }

    override fun createNewIssue(newIssue: Issue) {
        fireStore.collection(FIREBASE_COLLECTION_ISSUE_PATH).document().set(newIssue)
            .addOnSuccessListener {
                println("성공")
            }
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

        const val FIREBASE_COLLECTION_USER_PATH = "User"
        const val FIREBASE_COLLECTION_LABEL_PATH = "Label"
        const val FIREBASE_COLLECTION_ISSUE_PATH = "Issue"
    }

}