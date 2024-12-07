import retrofit2.http.GET
import retrofit2.http.Query

interface BookApiService {
    @GET("volumes")
    suspend fun searchBooks(@Query("q") query: String): BooksResponse
}

const val BASE_URL = "https://www.googleapis.com/books/v1/" 