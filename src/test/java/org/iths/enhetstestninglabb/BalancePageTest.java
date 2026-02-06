package org.iths.enhetstestninglabb;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BalancePageTest {
    static Playwright playwright;
    static Browser browser;
    BrowserContext context;
    Page page;

    @BeforeAll
    static void setupAll() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch();
    }

    @AfterAll
    static void tearDownAll() {
        browser.close();
        playwright.close();
    }

    @BeforeEach
    void setup() {
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterEach
    void cleanup() {
        context.close();
    }

    @Test
    @DisplayName("Balance page loads successfully")
    void balancePageLoads() {
        page.navigate("http://localhost:8080/balance");
        assertEquals("Saldo", page.title());
    }

    @Test
    @DisplayName("Balance is displayed on the page")
    void balanceIsDisplayed() {
        page.navigate("http://localhost:8080/balance");
        String text = page.locator("#balance").textContent();
        assertTrue(text.contains("Saldo"));
    }
}
