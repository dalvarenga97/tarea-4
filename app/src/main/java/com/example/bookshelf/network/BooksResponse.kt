data class BooksResponse(
    val items: List<VolumeInfo>
)

data class VolumeInfo(
    val id: String,
    val volumeInfo: BookInfo
)

data class BookInfo(
    val title: String,
    val authors: List<String>?,
    val description: String?,
    val imageLinks: ImageLinks?
)

data class ImageLinks(
    val thumbnail: String?
) 