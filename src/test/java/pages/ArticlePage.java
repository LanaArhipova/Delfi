package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ArticlePage {

    private BaseFunction baseFunction;
    private final Logger LOGGER = LogManager.getLogger(ArticlePage.class);

    private final By ARTICLE_PAGE_TITLE = By.xpath("//h1[contains(@class,'text-size-22')]");
    private final By ARTICLE_PAGE_COMMENTS = By.xpath("//a[contains(@class,'text-size-md-28 text-red-ribbon')]");

    public ArticlePage(BaseFunction baseFunction) {
        this.baseFunction = baseFunction;
        LOGGER.info("Start working in Article Page class");
        WebElement title = baseFunction.getElementList(ARTICLE_PAGE_TITLE).get(0);
        Assertions.assertNotNull(title, "There is no title on Article page");
        WebElement comments = baseFunction.getElementList(ARTICLE_PAGE_COMMENTS).get(0);
        Assertions.assertNotNull(comments, "There is no comment on Article page");
    }

    public String getTitleText() {
        List<WebElement> titleList = baseFunction.getElementList(ARTICLE_PAGE_TITLE);
        if (!titleList.isEmpty()) {
            return titleList.get(0).getText().trim();
        } else {
            return null;
        }
    }

    public Integer getCommentCount() {
        List<WebElement> commentList = baseFunction.getElementList(ARTICLE_PAGE_COMMENTS);
        if (!commentList.isEmpty()) {
            String commentCount = baseFunction.removeBrackets(commentList.get(0).getText());
            return Integer.valueOf(commentCount);
        } else {
            Integer commentCount = 0;
            return commentCount;
        }
    }

    public CommentPage openCommentPage() {
        WebElement element = baseFunction.getElement(ARTICLE_PAGE_COMMENTS);
        baseFunction.clickOnElement(element);
        return new CommentPage(baseFunction);
    }

}