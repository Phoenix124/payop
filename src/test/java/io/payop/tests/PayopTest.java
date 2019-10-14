package io.payop.tests;

import io.payop.pages.FeePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class PayopTest extends BaseTest {


    @Test
    public void standardWithoutConversion() {
        String tabName = "Cards International";
        String amount = "1000";
        String orderId = String.valueOf(getRandomOrderId());

        FeePage feePage = loginToPayop()
                .navigateToPaymentMethods()
                .navigateToFeePage()
                .navigateToFeeTab(tabName)
                .setPercentToField("100")
                .navigateToProjectsListPage()
                .navigateToRestPage()
                .setAmount(amount)
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
                .setCardholderName("Mykola Lavryshyn")
                .navigateToConfirm3DSPaymentPage()
                .connfirm3DSPayment()
                .navigateToPaymentMethodsPage()
                .navigateToFeePage()
                .navigateToFeeTab(tabName);

        double feePercentage = Double.parseDouble(feePage.getCardCommission().replaceAll("[^0-9]", ""));

        String sumOfTransaction = feePage.navigateToProjectsListPage()
                .navigateToTransactionsPage()
                .getSumOfTransaction();

        double actualSumOfTransaction = Double.parseDouble(sumOfTransaction.replaceAll("[^0-9.]", ""));
        double expectedAmount = Double.parseDouble(amount);
        double expectedSumOfTransaction = (expectedAmount - (((feePercentage / 100) * expectedAmount) + 1.0));

        Assert.assertEquals(actualSumOfTransaction, expectedSumOfTransaction);
    }

    private int getRandomOrderId() {
        Random random = new Random(5);
        return random.nextInt();
    }
}
