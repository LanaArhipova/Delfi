package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CommentPage {

    private BaseFunction baseFunction;
    private final Logger LOGGER = LogManager.getLogger(CommentPage.class);

    private final By COMMENT_PAGE_TITLE = By.xpath("//*[@class = 'article-title']");
    private final By COMMENT_PAGE_COMMENT_COUNT = By.className("type-cnt");

    public CommentPage(BaseFunction baseFunction) {
        this.baseFunction = baseFunction;
        LOGGER.info("Start working in Comment Page class");
        WebElement title = baseFunction.getElementList(COMMENT_PAGE_TITLE).get(0);
        Assertions.assertNotNull(title, "There is no title on Article page");
        WebElement comments = baseFunction.getElementList(COMMENT_PAGE_COMMENT_COUNT).get(0);
        Assertions.assertNotNull(comments, "There is no comment on Article page");
    }

    public String getTitleText() {
        List<WebElement> titleList = baseFunction.getElementList(COMMENT_PAGE_TITLE);
        if (!titleList.isEmpty()) {
            return titleList.get(0).getText().trim();
        } else {
            return null;
        }
    }

    public Integer getCommentCount() {
        List<WebElement> commentList = baseFunction.getElementList(COMMENT_PAGE_COMMENT_COUNT);
        if (!commentList.isEmpty()) {
            Integer commentCountAnonymous = Integer.valueOf(baseFunction.removeBrackets(commentList.get(0).getText()));
            Integer commentCountRegistered = Integer.valueOf(baseFunction.removeBrackets(commentList.get(1).getText()));
            return commentCountAnonymous + commentCountRegistered;
        } else {
            Integer commentCount = 0;
            return commentCount;
        }
    }

}