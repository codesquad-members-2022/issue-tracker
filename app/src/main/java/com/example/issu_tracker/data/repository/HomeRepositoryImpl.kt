package com.example.issu_tracker.data.repository


import com.example.issu_tracker.data.IssueDto
import com.example.issu_tracker.data.IssueList
import com.example.issu_tracker.data.toIssue
import com.google.firebase.firestore.DocumentSnapshot
import com.example.issu_tracker.data.network.NetworkResult
import com.example.issu_tracker.data.network.NetworkResult.Companion.EMPTY
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val fireStore: FirebaseFirestore
) : HomeRepository {

    private var lastVisibleDocument: DocumentSnapshot? = null
    private val collectionData =
        fireStore.collection(FIREBASE_COLLECTION_ISSUE_PATH).orderBy("title")

    override suspend fun loadFirstPageIssues(): List<IssueList> {
        val list = mutableListOf<IssueList>()
        val issueData = collectionData.limit(PAGE_NUMBER).get().await()

        lastVisibleDocument = issueData.last()

        issueData.documents.forEach {
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

        println(lastVisibleDocument)

        return list
    }

    override suspend fun loadNextPageIssues(currentPage: Int): List<IssueList> {
        val list = mutableListOf<IssueList>()
        val lastVisibleDoc = fireStore.collection(FIREBASE_COLLECTION_ISSUE_PATH).orderBy("title")
            .limit(currentPage * PAGE_NUMBER).get().await().documents.last()
        val issueData = collectionData
//            .startAfter(lastVisibleDocument)
            .startAfter(lastVisibleDoc)
            .limit(PAGE_NUMBER).get().await()

        println("해보자 $currentPage")

        issueData.documents.forEach {
            val issueObj = it.toObject(IssueDto::class.java)
            issueObj?.id = it.id
            issueObj?.let { issueDto ->
                issueDto.toIssue()?.let { issue ->
                    list.add(issue)
                }
                // 데이터를 추가하는 코드
                // fireStore.collection(FIREBASE_COLLECTION_ISSUE_PATH).document().set(it1)
                
        lastVisibleDocument = issueData.last()

        println("${issueData.last()["id"]}")
        println(lastVisibleDocument)

        return list
    }
                
    override suspend fun loadIssues(): NetworkResult<List<Issue>> {
        try {
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
            
            return if (list.isEmpty()) {
                NetworkResult.Error(EMPTY)
            } else NetworkResult.Success(list)

        } catch (e: Exception) {
            return NetworkResult.Exception(e)
        }
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
        const val PAGE_NUMBER: Long = 10
    }
}
