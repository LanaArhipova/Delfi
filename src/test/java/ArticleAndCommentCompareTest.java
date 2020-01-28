import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import pages.ArticlePage;
import pages.BaseFunction;
import pages.CommentPage;
import pages.HomePage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArticleAndCommentCompareTest {

    private BaseFunction baseFunction = new BaseFunction();
    private final Logger LOGGER = LogManager.getLogger(ArticleAndCommentCompareTest.class);
    private final String URL = "https://delfi.lv";
    private int articleNumber = 0;

    @Test
    public void titleAndCommentsTest() {

        LOGGER.info("Open home page");
        baseFunction.goToUrl(URL);

        HomePage homePage = new HomePage(baseFunction);

        LOGGER.info("Get title text for article No " + articleNumber);
        String homePageTitle = homePage.getTitleText(articleNumber);

        LOGGER.info("Get article comment count");
        Integer homePageComments = homePage.getCommentCount(articleNumber);

        LOGGER.info("Open article page");
        ArticlePage articlePage = homePage.openArticlePage(articleNumber);

        LOGGER.info("Get article title text");
        String articlePageTitle = articlePage.getTitleText();

        LOGGER.info("Get comment count");
        Integer articlePageComments = articlePage.getCommentCount();

        LOGGER.info("Check titles");
        assertEquals(homePageTitle, articlePageTitle, "Title on article page isn't the same than on Home page");

        LOGGER.info("Check comment count");
        assertEquals(homePageComments, articlePageComments, "Comment count on article page isn't the same than on Home page!");

        LOGGER.info("Open article page");
        CommentPage commentPage = articlePage.openCommentPage();

        LOGGER.info("Get article title text");
        String commentPageTitle = commentPage.getTitleText();

        LOGGER.info("Get comment count");
        Integer commentPageComments = commentPage.getCommentCount();

        LOGGER.info("Check titles");
        assertEquals(articlePageTitle, commentPageTitle, "Title on comment page isn't the same than on Article page");

        LOGGER.info("Check comment count");
        assertEquals(articlePageComments, commentPageComments, "Comment count on comment page isn't the same than on Article page!");
    }

    @AfterEach
    public void closeBrowser() {
        LOGGER.info("Close browser");
        baseFunction.quit();
    }

}