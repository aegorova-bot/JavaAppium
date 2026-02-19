package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    private static final String name_of_folder = "Learning programming";
    private static final String
            login = "Aleksandra Egorova",
            password = "Lena@9099";


    @Test
    public void testSaveFirstArticleToMyList()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement("Java (programming language)");
        String article_title = ArticlePageObject.getArticleTitle("Java (programming language)");

        if(Platform.getInstance().isAndroid())
        {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        }
        else
        {
            ArticlePageObject.getArticlesToMySaved();
        }
        if(Platform.getInstance().isMW())
        {
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            ArticlePageObject.waitForTitleElement("Object-oriented programming language");
            Assert.assertEquals("We are not at the same page after login",
                    article_title,
                    ArticlePageObject.getArticleTitle("Java (programming language)"));
        }

        if(Platform.getInstance().isAndroid())
        {
            ArticlePageObject.goBackToMainScreen();
            ArticlePageObject.goBackToMainScreen();
        }
        else
        {
            ArticlePageObject.goBackToMainScreen();
        }

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();
        NavigationUI.clickSavedArticles();
        MyListPageObject MyListPageObject = MyListPageObjectFactory.get(driver);
        if(Platform.getInstance().isAndroid())
        {
            MyListPageObject.openFolderByName(name_of_folder);

        }
        else if(Platform.getInstance().isIos())
        {
            MyListPageObject.closePopUp();
        }
        MyListPageObject.swipeByArticleToDelete(article_title);

    }

    @Test
    public void testSaveTwoArticlesToMyListDz()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement("Java (programming language)");
        String article_title = ArticlePageObject.getArticleTitle("Java (programming language)");

        if(Platform.getInstance().isAndroid())
        {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        }
        else
        {
            ArticlePageObject.getArticlesToMySaved();
        }

        if(Platform.getInstance().isMW())
        {
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            ArticlePageObject.waitForTitleElement("Object-oriented programming language");
            Assert.assertEquals("We are not at the same page after login",
                    article_title,
                    ArticlePageObject.getArticleTitle("Java (programming language)"));
        }

        ArticlePageObject.goBackToMainScreen();

        if(Platform.getInstance().isAndroid())
        {
            SearchPageObject.waitForElementAndClear();
        } else
        {
            SearchPageObject.initSearchInput();
        }
        SearchPageObject.typeSearchLine("Python");
        SearchPageObject.clickByArticleWithSubstring("Python (programming language)");
        ArticlePageObject.waitForTitleElement("Python (programming language)");
        if(Platform.getInstance().isAndroid())
        {
            ArticlePageObject.addArticleToExistingList();
        } else
        {
            ArticlePageObject.getArticlesToMySaved();
        }

        if(Platform.getInstance().isAndroid())
        {
            ArticlePageObject.goBackToMainScreen();
            ArticlePageObject.goBackToMainScreen();
        }
        else if(Platform.getInstance().isIos())
        {
            ArticlePageObject.goBackToMainScreen();
        }
        String article_title2 = null;

        if(Platform.getInstance().isMW())
        {
            article_title2 = ArticlePageObject.getArticleTitle("Python (programming language)");
        }
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();
        NavigationUI.clickSavedArticles();
        MyListPageObject MyListPageObject = MyListPageObjectFactory.get(driver);
        if(Platform.getInstance().isAndroid())
        {
            MyListPageObject.openFolderByName(name_of_folder);

        }
        else if(Platform.getInstance().isIos())
        {
            MyListPageObject.closePopUp();
        }
        if((Platform.getInstance().isAndroid()) || Platform.getInstance().isIos())
        {
            article_title2 = ArticlePageObject.getArticleTitle("Python (programming language)");
        }
        MyListPageObject.swipeByArticleToDelete(article_title2);
        MyListPageObject.checkTheSecondArticleIsPresent("Object-oriented programming language");
    }

}
