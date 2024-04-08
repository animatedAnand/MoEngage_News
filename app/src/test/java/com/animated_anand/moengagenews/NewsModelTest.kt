import com.animated_anand.moengagenews.model.News
import org.junit.Assert.assertEquals
import org.junit.Test

class NewsModelTest {

    @Test
    fun testNewsModel() {
        val news = News(
            sourceId = "123",
            sourceName = "Test Source",
            author = "John Doe",
            title = "Test News Title",
            description = "Test News Description",
            url = "https://example.com",
            urlToImage = "https://example.com/image.jpg",
            publishedAt = "2022-01-01T12:00:00Z",
            content = "Lorem ipsum dolor sit amet."
        )
        assertEquals("123", news.sourceId)
        assertEquals("Test Source", news.sourceName)
        assertEquals("John Doe", news.author)
        assertEquals("Test News Title", news.title)
        assertEquals("Test News Description", news.description)
        assertEquals("https://example.com", news.url)
        assertEquals("https://example.com/image.jpg", news.urlToImage)
        assertEquals("2022-01-01T12:00:00Z", news.publishedAt)
        assertEquals("Lorem ipsum dolor sit amet.", news.content)
    }
}
