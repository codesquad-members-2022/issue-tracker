package com.example.issu_tracker.data.repository

import com.example.issu_tracker.data.IssueDto
import com.example.issu_tracker.data.IssueList
import com.example.issu_tracker.data.toIssue
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val fireStore: FirebaseFirestore
) : HomeRepository {

    private var lastVisibleDoc: DocumentSnapshot? = null

    /* override suspend fun loadIssues(): List<Issue> {
         val list = mutableListOf<Issue>()
         val collectionData = fireStore.collection(FIREBASE_COLLECTION_ISSUE_PATH).get().await()

         collectionData.documents.forEach {
             val issueObj = it.toObject(IssueDto::class.java)
             issueObj?.id = it.id
             issueObj?.let { it1 ->
                 it1.toIssue()?.let { it2 -> list.add(it2) }
                 // 데이터를 추가하는 코드
                 // fireStore.collection(FIREBASE_COLLECTION_ISSUE_PATH).document().set(it1)
             }
         }
         return list
     }*/

    override suspend fun loadFirstPageIssues(): List<IssueList> {
        val list = mutableListOf<IssueList>()
        val collectionData =
            fireStore.collection(FIREBASE_COLLECTION_ISSUE_PATH).orderBy("title").limit(10).get()
                .await()
        lastVisibleDoc = collectionData.documents.last()

        /*  val collectionData: QuerySnapshot = if (lastVisibleDoc != null) {
              fireStore.collection(FIREBASE_COLLECTION_ISSUE_PATH).limit(10).get().await()
          } else {
              fireStore.collection(FIREBASE_COLLECTION_ISSUE_PATH).startAfter(lastVisibleDoc)
                  .limit(10).get().await()
          }*/

        collectionData.documents.forEach {
            val issueObj = it.toObject(IssueDto::class.java)
            issueObj?.id = it.id
            issueObj?.let { issueDto ->
                issueDto.toIssue()?.let { issue ->
                    list.add(issue)
                }
                // 데이터를 추가하는 코드
                // fireStore.collection(FIREBASE_COLLECTION_ISSUE_PATH).document().set(issueDto)
            }
        }
        return list
    }

    override suspend fun loadNextPageIssues(): List<IssueList> {
        val list = mutableListOf<IssueList>()
        println(lastVisibleDoc)
        val test = fireStore.collection(FIREBASE_COLLECTION_ISSUE_PATH).orderBy("title").limit(10).get().await().documents.last()
        val collectionData =
            fireStore.collection(FIREBASE_COLLECTION_ISSUE_PATH)
                .orderBy("title")
                .startAfter(lastVisibleDoc)
                .limit(10)
                .get()
                .await()

        println("진짜? ${collectionData.isEmpty}")


         collectionData.documents.forEach {
             val issueObj = it.toObject(IssueDto::class.java)
             issueObj?.id = it.id
             issueObj?.let { issueDto ->
                 issueDto.toIssue()?.let { issue ->
                     list.add(issue) }
                 // 데이터를 추가하는 코드
                 // fireStore.collection(FIREBASE_COLLECTION_ISSUE_PATH).document().set(it1)
             }
         }

         if (collectionData.documents.isNotEmpty()) {
             lastVisibleDoc = collectionData.documents.last()
         }

        return list
    }

    override suspend fun updateIssueState(itemId: String, boolean: Boolean) {

        fireStore.collection(FIREBASE_COLLECTION_ISSUE_PATH).document(itemId)
            .update("state", false).await()

    }

    override suspend fun deleteIssueList(list: List<IssueList>) {
        CoroutineScope(Dispatchers.IO).launch {
            for (i in list) {
                if (i is IssueList.Issue)
                    fireStore.collection(FIREBASE_COLLECTION_ISSUE_PATH).document(i.id).delete()
            }
        }.join()
    }

    override suspend fun updateIssueListState(list: List<IssueList>, boolean: Boolean) {
        CoroutineScope(Dispatchers.IO).launch {
            for (i in list) {
                if (i is IssueList.Issue)
                    fireStore.collection(FIREBASE_COLLECTION_ISSUE_PATH).document(i.id)
                        .update("state", false)
            }
        }.join()
    }


    companion object {
        const val FIREBASE_COLLECTION_ISSUE_PATH = "Issue"
        const val FIREBASE_COLLECTION_FRIEND_PATH = "User"
    }
}
