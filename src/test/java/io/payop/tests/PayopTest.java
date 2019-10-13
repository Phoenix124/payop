package io.payop.tests;

import org.testng.annotations.Test;

import java.util.Random;

public class PayopTest extends BaseTest {


    @Test
    public void standardWithoutConversion() {
        String tabName = "Cards International";
        String orderId = String.valueOf(getRandomOrderId());

        loginToPayop()
                .navigateToPaymentMethods()
                .navigateToFeePage()
                .navigateToFeeTab(tabName)
                .setPercentToField("100")
                .navigateToProjectsListPage()
                .navigateToRestPage()
                .setAmount("1000")
                .setCurrency("usd")
                .setOrderId(orderId)
                .setResultUrl("https://app.stage.payop.com/en/profile/projects/rest/538aa33d-c906-427e-be3f-a2d1739c1599")
                .setFailPath("https://app.stage.payop.com/en/profile/projects/rest/538aa33d-c906-427e-be3f-a2d1739c1599")
                .generateConfig()
                .showPaymentPage()
                .navigateToPaymentCheckout(userDto.email)
                .setCardNumber("4012888888881881")
                .setCvvNumber("123")
                .setExpirationDate("12/20")
                .confirmPayment();


    }

    private int getRandomOrderId() {
        Random random = new Random(5);
        return random.nextInt();
    }
}
