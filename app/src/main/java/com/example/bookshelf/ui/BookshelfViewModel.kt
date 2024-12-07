import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BookshelfViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<BookshelfUiState>(BookshelfUiState.Loading)
    val uiState: StateFlow<BookshelfUiState> = _uiState.asStateFlow()

    fun searchBooks(query: String) {
        viewModelScope.launch {
            try {
                _uiState.value = BookshelfUiState.Loading
                val response = bookApiService.searchBooks(query)
                val books = response.items.map { item ->
                    Book(
                        id = item.id,
                        title = item.volumeInfo.title,
                        authors = item.volumeInfo.authors ?: emptyList(),
                        description = item.volumeInfo.description,
                        imageUrl = item.volumeInfo.imageLinks?.thumbnail
                    )
                }
                _uiState.value = BookshelfUiState.Success(books)
            } catch (e: Exception) {
                _uiState.value = BookshelfUiState.Error
            }
        }
    }
}

sealed interface BookshelfUiState {
    object Loading : BookshelfUiState
    data class Success(val books: List<Book>) : BookshelfUiState
    object Error : BookshelfUiState
} 