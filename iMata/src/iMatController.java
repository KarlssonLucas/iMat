import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import se.chalmers.cse.dat216.project.*;

import java.net.URL;
import java.util.*;

import static javafx.scene.paint.Color.GREEN;
import static javafx.scene.paint.Color.RED;
import static se.chalmers.cse.dat216.project.ProductCategory.*;


public class  iMatController implements Initializable, ShoppingCartListener {

    //TODO
 /* NEED FOR PROG
    - välja font
    - gå vidare med order
    - logotyp
    - rabatterade produkter logo svart istället
    - hjälp fönstret
    - spara upon exit (dubbelkolla funktionalitet)
 */

    @FXML private AnchorPane paneTop;
    @FXML private ImageView logo;
    @FXML private Label logoText;
    @FXML private Button minaSidorButton;
    @FXML private Button checkoutButton;
    @FXML private Button helpButton;
    @FXML private Button hemButton;
    @FXML private StackPane background;
    @FXML private TextField searchField;

    @FXML private StackPane gridPane;
    @FXML private Button reaButton;
    @FXML private AnchorPane bannerImage;
    @FXML private Label categoryBannerLabel;
    @FXML private ImageView categoryBannerImage;

    @FXML private FlowPane productFlow;
    @FXML private ScrollPane categoryScroll;
    @FXML private ScrollPane scrollItemView;
    @FXML private GridPane gridCategory;
    @FXML private Label cartTotal;

    @FXML private FlowPane ordersFlowPane;

    @FXML public Button bbb0;
    @FXML public Button bbb01;
    @FXML public Button bbb1;
    @FXML public Button bbb2;
    @FXML public Button bbb3;
    @FXML public Button bbb4;
    @FXML public Button bbb5;
    @FXML public Button bbb6;
    @FXML public Button bbb7;
    @FXML public Button bbb8;
    @FXML public Button bbb9;
    @FXML public Button bbb10;

    //KUNDVAGN
    @FXML private Label kassanTxt2;
    @FXML private Label kassanTxt21;
    @FXML private FlowPane checkoutFlow;
    @FXML private AnchorPane kassanTopPane;
    @FXML private AnchorPane varukorg;

    //MINA SIDOR
    @FXML private AnchorPane minaSidor;
    @FXML private AnchorPane minaSidorInfoPane;
    @FXML private Button skapaKontoBtn;
    @FXML private Label rodText;
    @FXML private Label tidigareKopTxt;
    @FXML private ImageView cardImage;
    @FXML private Button resetButton;

    @FXML private TextField forNamnField;
    @FXML private TextField efterNamnField;
    @FXML private TextField epostField;
    @FXML private TextField telefonField;
    @FXML private TextField adressField;
    @FXML private TextField adressNumberField;
    @FXML private TextField postnummerField;
    @FXML private TextField kortnummerField;
    @FXML private TextField kortNamnField;
    @FXML private TextField kortDatumMonthField;
    @FXML private TextField kortDatumYearField;
    @FXML private TextField cvcField;
    @FXML private AnchorPane registerDemand;
    @FXML private Label textRegisterPlease;

    //ORDERING
    @FXML private AnchorPane orderConfirmation;
    @FXML private TextField confirmationName;
    @FXML private TextField confirmationAdress;
    @FXML private TextField confirmationAdressNumber;
    @FXML private TextField confirmationCardNumber;
    @FXML private TextField confirmationExpire;
    @FXML private TextField confirmationCvc;
    @FXML private TextField confirmationArrival;
    @FXML private Button setArrival1251730;
    @FXML private Button setArrival1251800;
    @FXML private Button setArrival1351730;
    @FXML private Button setArrival1351800;
    @FXML private Button setArrival1451730;
    @FXML private Button setArrival1451800;
    @FXML private AnchorPane orderSucess;
    @FXML private Label textSucessOrder;

    //HELP
    @FXML private AnchorPane helpWindow;
    @FXML private ImageView imageHelp;
    @FXML private Label helpText;

    private final IMatDataHandler dh = IMatDataHandler.getInstance();
    public ArrayList<Product> reaProducts = new ArrayList<>();
    public ArrayList<String> helpImages = new ArrayList<>();
    public ArrayList<String> helpTexts = new ArrayList<>();
    private int nHelpImage = 0;
    private int nHelpText = 0;

    //MINA SIDOR
    private List<Order> orderlista = dh.getOrders();
    private Map<Integer,OrderItem> orderListItemMap = new HashMap<Integer, OrderItem>();
    private OrderItem orderItem;
    public Customer customer = dh.getCustomer();
    public CreditCard creditCard = dh.getCreditCard();

    //Startsida
    @FXML private AnchorPane hem;
    public ShoppingCart shoppingCart = dh.getShoppingCart();

    private final List<Product> productList = dh.getProducts();
    private List<Product> currentProductList = dh.getProducts();

    private Map<String,iMatItem> recipeListItemMap = new HashMap<String, iMatItem>();
    private iMatItem iMatItem;

    //KASSAN
    private Map<String, iMatCheckoutItem> checkoutListItemMap = new HashMap<String, iMatCheckoutItem>();
    private iMatCheckoutItem checkoutItem;
    public ArrayList<ShoppingItem> si = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if (!dh.isCustomerComplete()) {
            minaSidorButton.setText("Registrera dig");
            skapaKontoBtn.setText("Skapa konto");
        } else {
            savedRestore();
        }
        hemButton.setText("Hem ");
        checkoutButton.setText("....");
        helpButton.setText("Hjälp");
        reaButton.setText("Shoppa");

        setReaProducts();
        setHelpImages();
        all_category();
        updateCart();
        updateHelpText();
        updateImageHelp();

        scrollItemView.setFitToWidth(true);
        scrollItemView.setFitToHeight(true);


        populateOrderHashMap();
        updateOrders();

    }

    public void setHelpImages() {
        String s;
        s = "/resources/iMatHem.png";
        helpImages.add(s);
        s = "/resources/iMatKategorier.png";
        helpImages.add(s);
        s = "/resources/iMatLäggTill.png";
        helpImages.add(s);
        s = "/resources/iMatSök.png";
        helpImages.add(s);
        s = "/resources/iMatMinaSidor.png";
        helpImages.add(s);
        s = "/resources/iMatMinaSidorUppgifter.png";
        helpImages.add(s);
        s = "/resources/iMatMinaSidorTidigareKöp.png";
        helpImages.add(s);
        s = "/resources/iMatMinaSidorReset.png";
        helpImages.add(s);
        s = "/resources/iMatKundvagn.png";
        helpImages.add(s);
        s = "/resources/iMatKundvagnItem.png";
        helpImages.add(s);
        s = "/resources/iMatKundvagnGåvidare.png";
        helpImages.add(s);
        s = "/resources/iMatOrderLeverans.png";
        helpImages.add(s);
        s = "/resources/iMatOrderCofirm.png";
        helpImages.add(s);
        s = "/resources/iMatOrderOrder.png";
        helpImages.add(s);

        String a;
        a = "Tar dig till applikationens startsida.";
        helpTexts.add(a);
        a = "Här kan du välja vilken kategori du vill visa i produktflödet.";
        helpTexts.add(a);
        a = "Välj antalet produkter du vill lägga i kundvagnen antingen genom att skriva en siffra eller använda + och - knapparna";
        helpTexts.add(a);
        a = "Här kan du söka efter en produkt i sortimentet";
        helpTexts.add(a);
        a = "Tar dig till dina sidor, fönstret där du kan se dina uppgifter eller skapa ett konto. Samt se dina tidigare köp i form av kvitton.";
        helpTexts.add(a);
        a = "Här kan skapa ett konto samt ändra dina uppgifter.";
        helpTexts.add(a);
        a = "Se tidigare köp du gjort och lägg snabbt till samma produkter i din köplista.";
        helpTexts.add(a);
        a = "Återställer hela programmet och tar bort alla dina kontouppgifter";
        helpTexts.add(a);
        a = "Tar dig till kundvagnen";
        helpTexts.add(a);
        a = "Ändra antalet av en produkt i kundvagnen och/eller ta bort den";
        helpTexts.add(a);
        a = "Tar dig vidare till nästa fönster i köpet eller tillbaka till startsidan.";
        helpTexts.add(a);
        a = "Bestäm när du vill att din beställning ska levereras.";
        helpTexts.add(a);
        a = "Bekräfta att dina uppgifter stämmer, gör det inte det kan du ta dig till mina sidor och ändra de";
        helpTexts.add(a);
        a = "Den här knappen bekräftar köpet och efter du tryckt på den är du färdig.";
        helpTexts.add(a);



    }

    public void nextImage() {
        if (nHelpImage < helpImages.size() - 1) {
            nHelpImage++;
            nHelpText++;
            updateHelpText();
            updateImageHelp();
        }
    }

    public void previousImage() {
        if (nHelpImage > 0) {
            nHelpImage--;
            nHelpText--;
            updateHelpText();
            updateImageHelp();
        }
    }

    public void updateHelpText() {
        helpText.setText(helpTexts.get(nHelpText));
    }

    public void updateImageHelp() {
        imageHelp.setImage(new Image(helpImages.get(nHelpImage)));
    }

    public void setReaProducts() {

        reaProducts.add(productList.get(15));
        reaProducts.add(productList.get(17));
        reaProducts.add(productList.get(25));
        reaProducts.add(productList.get(2));
        reaProducts.add(productList.get(100));
        reaProducts.add(productList.get(55));

        if (dh.isFirstRun()) {
            for (Product p : reaProducts) {
                double a = p.getPrice() * 0.5;
                p.setPrice(a);
            }
        }
    }

    @FXML private void resetProgram() {
        dh.reset();
        Platform.exit();
    }

    @FXML
    private void closeRegisterDemand() {
        registerDemand.toBack();
    }

    @FXML private void showHelp() {
        helpWindow.toFront();
        minaSidor.toBack();
        updateImageHelp();
    }

    @FXML
    private void startOrdering() {
        if (shoppingCart.getTotal() != 0.0) {
            if (dh.isCustomerComplete()) {
                orderConfirmation.toFront();

                confirmationAdress.setText(customer.getAddress());
                confirmationAdressNumber.setText(customer.getPostAddress());
                confirmationCardNumber.setText(creditCard.getCardNumber());
                confirmationCvc.setText(String.valueOf(creditCard.getVerificationCode()));
                confirmationExpire.setText(String.valueOf(creditCard.getValidMonth()) + "/" + String.valueOf(creditCard.getValidYear()));
                confirmationName.setText(creditCard.getHoldersName());
            } else {
                registerDemand.toFront();
            }
        }
    }

    @FXML
    private void finishOrder() {
        if (!confirmationArrival.getText().isEmpty()) {
            Order orderObject = new Order();
            Date date = new Date();
            int i = (int) (Math.random() * 10000);
            ArrayList<ShoppingItem> si = new ArrayList<ShoppingItem>();

            for (ShoppingItem shoppingItem : shoppingCart.getItems()) {
                si.add(shoppingItem);
            }

            orderObject.setDate(date);
            orderObject.setItems(si);
            orderObject.setOrderNumber(i);

            orderlista.add(orderObject);

            populateOrderHashMap();
            updateOrders();

            shoppingCart.clear();
            updateCart();
            updateCheckout();
            populateCheckout();

            textSucessOrder.setText("Ditt köp har gått igenom och dina varor kommer anlända på din adress den " + confirmationArrival.getText());
            orderSucess.toFront();
        }
    }

    @FXML
    private void onClickGridOrder(Event event) {
        EventTarget target = event.getTarget();

        Date date = new Date();

        if (target.equals(setArrival1251730)) {
            confirmationArrival.setText("12 maj 17:30");
        }
        if (target.equals(setArrival1251800)) {
            confirmationArrival.setText("12 maj 18:00");
        }
        if (target.equals(setArrival1351730)) {
            confirmationArrival.setText("13 maj 17:30");
        }
        if (target.equals(setArrival1351800)) {
            confirmationArrival.setText("13 maj 18:00");
        }
        if (target.equals(setArrival1451730)) {
            confirmationArrival.setText("14 maj 17:30");
        }
        if (target.equals(setArrival1451800)) {
            confirmationArrival.setText("14 maj 18:00");
        }
    }

    @FXML
    public void searchProduct() {
        String s = String.valueOf(searchField.getCharacters());
        currentProductList = dh.findProducts(s);
        search_category(s);
        hemShow();
    }
    @FXML
    public void hemShow() {
        hem.toFront();
    }

    @FXML
    public void varukorgShow() {
        populateCheckout();
        updateCheckout();

        varukorg.toFront();
    }

    //Mina sidor metoder
    @FXML
    public void minaSidorShow() {
        if (dh.isCustomerComplete()) {
            skapaKontoBtn.setText("Uppdatera kontouppgifter");
        }
        minaSidor.toFront();
    }

    @FXML
    public void minaSidorHide() {
        minaSidor.toBack();
    }

    public void savedRestore() {
        minaSidorButton.setText("Mina Sidor");
        skapaKontoBtn.setText("Uppdatera kontouppgifter");

        adressField.setText(customer.getAddress());
        adressNumberField.setText(customer.getPostAddress());
        epostField.setText(customer.getEmail());
        forNamnField.setText(customer.getFirstName());
        efterNamnField.setText(customer.getLastName());
        telefonField.setText(customer.getMobilePhoneNumber());
        postnummerField.setText(customer.getPostCode());

        setCardImage(creditCard.getCardNumber());
        kortnummerField.setText(creditCard.getCardNumber());
        kortNamnField.setText(creditCard.getHoldersName());
        kortDatumMonthField.setText(String.valueOf(creditCard.getValidMonth()));
        kortDatumYearField.setText(String.valueOf(creditCard.getValidYear()));
        cvcField.setText(String.valueOf(creditCard.getVerificationCode()));

    }

    public int getAmountProducts() {
        int total = 0;
        for (ShoppingItem s : shoppingCart.getItems()) {
            total = total + (int) s.getAmount();
        }
        return total;
    }

    public int getAmountGivenList(List<ShoppingItem> list) {
        int total = 0;
        for (ShoppingItem r : list) {
            total = total + (int) r.getAmount();
        }
        return total;
    }

    public double  getTotalGivenList(List<ShoppingItem> list) {
        double total = 0;
        for (ShoppingItem r : list) {
            total = total + r.getAmount() * r.getProduct().getPrice();
        }
        double roundOff = Math.round(total * 100.0) / 100.0;
        return roundOff;
    }

    private void populateOrderHashMap(){
        for (Order o: orderlista){
            orderItem = new OrderItem(o,this);
            orderListItemMap.put(o.getOrderNumber(),orderItem);
        }
    }

    private void updateOrders(){
        ordersFlowPane.getChildren().clear();
        for (Order o: orderlista) {
            ordersFlowPane.getChildren().add(orderListItemMap.get(o.getOrderNumber()));
        }
    }

    private void updateRecipeList(){
        productFlow.getChildren().clear();
        for (Product r: currentProductList) {
            productFlow.getChildren().add(recipeListItemMap.get(r.getName()));
        }
    }

    private void populateHashMap(){
        for (Product product : currentProductList){
            iMatItem = new iMatItem(product, this);
            recipeListItemMap.put(product.getName(), iMatItem);
        }
    }

    public void updateCart() {
        double roundOff = Math.round(shoppingCart.getTotal() * 100.0) / 100.0;
        cartTotal.setText(String.valueOf(roundOff + " kr"));
        kassanTxt2.setText(getAmountProducts() + " varor");
        kassanTxt21.setText(String.valueOf(roundOff + " kr"));
    }

    public void updateCheckout(){
        checkoutFlow.getChildren().clear();
        for (ShoppingItem r : shoppingCart.getItems()) {
            checkoutFlow.getChildren().add(checkoutListItemMap.get(r.getProduct().getName()));
        }
    }

    public void populateCheckout() {
        for (ShoppingItem r : shoppingCart.getItems()){
            checkoutItem = new iMatCheckoutItem(r.getProduct(), this);
            checkoutListItemMap.put(r.getProduct().getName(), checkoutItem);
        }
    }

    public void removeDup() {
        List<ShoppingItem> sc = shoppingCart.getItems();
        double n = 0;

        for (int k = 0; k < sc.size()-1; k++) {
            ShoppingItem r = sc.get(k);
            for (int i = k + 1; i < sc.size(); i++) {
                ShoppingItem t = sc.get(i);
                if (r.getProduct().equals(t.getProduct())) {
                    n = r.getAmount() + t.getAmount();
                    shoppingCart.addProduct(r.getProduct(), n);
                    shoppingCart.removeItem(r);
                    shoppingCart.removeItem(t);
                }
            }
        }
    }

    //Category method calls

    public void all_category(){
        reaButton.setVisible(true);
        bannerImage.setVisible(false);
        categoryBannerImage.setImage(null);
        categoryBannerLabel.setText("");

        currentProductList = dh.getProducts();
        populateHashMap();
        updateRecipeList();
    }

    public void rea_category() {
        reaButton.setVisible(false);
        bannerImage.setVisible(true);
        categoryBannerImage.setImage(new Image("resources/rea.png"));
        categoryBannerLabel.setText("Rea");

        currentProductList = reaProducts;
        populateHashMap();
        updateRecipeList();

    }

    public void search_category(String text){
        reaButton.setVisible(false);
        bannerImage.setVisible(true);
        categoryBannerImage.setImage(new Image("resources/search.png"));
        categoryBannerLabel.setText(text);

        populateHashMap();
        updateRecipeList();
    }

    public void favoriter_category(){
        reaButton.setVisible(false);
        bannerImage.setVisible(true);
        categoryBannerLabel.setText("Favoriter");
        categoryBannerImage.setImage(new Image("resources/favoriterBanner.png"));

        currentProductList = dh.favorites();
        populateHashMap();
        updateRecipeList();
    }
    public void mejeri_category(){
        reaButton.setVisible(false);
        bannerImage.setVisible(true);
        categoryBannerLabel.setText("Mejeri & Ägg");
        categoryBannerImage.setImage(new Image("resources/dairyBanner.png"));

        currentProductList = dh.getProducts(DAIRIES);
        populateHashMap();
        updateRecipeList();
    }
    public void kott_category(){
        reaButton.setVisible(false);
        bannerImage.setVisible(true);
        categoryBannerLabel.setText("Kött");
        categoryBannerImage.setImage(new Image("resources/meatBanner.png"));

        currentProductList = dh.getProducts(MEAT);
        populateHashMap();
        updateRecipeList();
    }
    public void fisk_category(){
        reaButton.setVisible(false);
        bannerImage.setVisible(true);
        categoryBannerLabel.setText("Fisk");
        categoryBannerImage.setImage(new Image("resources/fishBanner.png"));

        currentProductList = dh.getProducts(FISH);
        populateHashMap();
        updateRecipeList();
    }
    public void frukt_category(){
        reaButton.setVisible(false);
        bannerImage.setVisible(true);
        categoryBannerLabel.setText("Frukt & Grönt");
        categoryBannerImage.setImage(new Image("resources/fruitBanner.png"));

        ArrayList<Product> temp = new ArrayList<Product>();

        for(Product p: productList){
            if (p.getCategory() == FRUIT || p.getCategory() == VEGETABLE_FRUIT || p.getCategory() == CITRUS_FRUIT || p.getCategory() == EXOTIC_FRUIT ||
                    p.getCategory() == CABBAGE || p.getCategory() == MELONS || p.getCategory() == ROOT_VEGETABLE || p.getCategory() == POD){
                temp.add(p);
            }
        }
        currentProductList = temp;
        populateHashMap();
        updateRecipeList();
    }

    public void kryddor_category(){
        reaButton.setVisible(false);
        bannerImage.setVisible(true);
        categoryBannerLabel.setText("Kryddor & Bär");
        categoryBannerImage.setImage(new Image("resources/spiceBanner.png"));

        ArrayList<Product> temp = new ArrayList<Product>();

        for(Product p: productList){
            if (p.getCategory() == HERB || p.getCategory() == BERRY){
                temp.add(p);
            }
        }
        currentProductList = temp;
        populateHashMap();
        updateRecipeList();
    }

    public void skafferi_category(){
        reaButton.setVisible(false);
        bannerImage.setVisible(true);
        categoryBannerLabel.setText("Skafferi");
        categoryBannerImage.setImage(new Image("resources/pantryBanner.png"));

        ArrayList<Product> temp = new ArrayList<Product>();

        for(Product p: productList){
            if (p.getCategory() == PASTA || p.getCategory() == POTATO_RICE || p.getCategory() == FLOUR_SUGAR_SALT ){
                temp.add(p);
            }
        }
        currentProductList = temp;
        populateHashMap();
        updateRecipeList();
    }

    public void bageri_category(){
        reaButton.setVisible(false);
        bannerImage.setVisible(true);
        categoryBannerLabel.setText("Bageri");
        categoryBannerImage.setImage(new Image("resources/bakeryBanner.png"));

        currentProductList  = dh.getProducts(BREAD);
        populateHashMap();
        updateRecipeList();
    }

    public void dryck_category(){
        reaButton.setVisible(false);
        bannerImage.setVisible(true);
        categoryBannerLabel.setText("Dryck");
        categoryBannerImage.setImage(new Image("resources/drinkBanner.png"));

        ArrayList<Product> temp = new ArrayList<Product>();

        for(Product p: productList){
            if (p.getCategory() == HOT_DRINKS || p.getCategory() == COLD_DRINKS ){

                temp.add(p);

            }
        }
        currentProductList = temp;
        populateHashMap();
        updateRecipeList();
    }

    public void godsaker_category(){
        reaButton.setVisible(false);
        bannerImage.setVisible(true);
        categoryBannerLabel.setText("Godsaker");
        categoryBannerImage.setImage(new Image("resources/candyBanner.png"));

        ArrayList<Product> temp = new ArrayList<Product>();

        for(Product p: productList){
            if (p.getCategory() == SWEET || p.getCategory() == NUTS_AND_SEEDS ){

               temp.add(p);

            }
        }
        currentProductList = temp;
        populateHashMap();
        updateRecipeList();
    }

    //End of category method calls

    public void startUser() {

    }

    public void create_user(){
        if(forNamnField.getText().isEmpty() || efterNamnField.getText().isEmpty() || epostField.getText().isEmpty() || telefonField.getText().isEmpty() || postnummerField.getText().isEmpty() ||
                adressField.getText().isEmpty() || kortnummerField.getText().isEmpty() || kortNamnField.getText().isEmpty() || kortDatumMonthField.getText().isEmpty() || kortDatumYearField.getText().isEmpty() || cvcField.getText().isEmpty() ){
            rodText.setTextFill(RED);
            if (dh.isCustomerComplete()) {
                rodText.setText("Fyll i samtliga uppgifter för att uppdatera ditt konto");
            } else {
                rodText.setText("Fyll i samtliga uppgifter för att skapa ett konto!");
            }
        } else{
            rodText.setTextFill(GREEN);
            if (dh.isCustomerComplete())  {
                rodText.setText("Du har uppdaterat dina uppgifter");
            } else {
                rodText.setText("Ditt konto är skapat!");
            }
            customer.setAddress(adressField.getText());
            customer.setPostAddress(adressNumberField.getText());
            customer.setEmail(epostField.getText());
            customer.setFirstName(forNamnField.getText());
            customer.setLastName(efterNamnField.getText());
            customer.setMobilePhoneNumber(telefonField.getText());
            customer.setPhoneNumber(telefonField.getText());
            customer.setPostCode(postnummerField.getText());

            setCardImage(kortnummerField.getText());
            creditCard.setCardNumber(kortnummerField.getText());
            creditCard.setHoldersName(kortNamnField.getText());
            creditCard.setValidMonth(Integer.parseInt(kortDatumMonthField.getText()));
            creditCard.setValidYear(Integer.parseInt(kortDatumYearField.getText()));
            creditCard.setVerificationCode(Integer.parseInt(cvcField.getText()));
            skapaKontoBtn.setText("Uppdatera kontouppgifter");
            minaSidorButton.setText("Mina sidor");
        }
    }

    private void setCardImage(String i) {

        Image icon = null;
        String iconPath;

        switch (String.valueOf(i.charAt(0))) {
            case "4":
                iconPath = "./resources/visa.png";
                icon = new Image(getClass().getClassLoader().getResourceAsStream(iconPath));
                creditCard.setCardType("Visa");
                break;
            case "5":
                iconPath = "./resources/mastercard.png";
                icon = new Image(getClass().getClassLoader().getResourceAsStream(iconPath));
                creditCard.setCardType("Mastercard");
                break;
            case "3":
                iconPath = "./resources/amex.png";
                icon = new Image(getClass().getClassLoader().getResourceAsStream(iconPath));
                creditCard.setCardType("Amex");
                break;
            case "9":
            case "8":
            case "7":
            case "6":
            case "2":
            case "1":
                iconPath = "./resources/card.png";
                icon = new Image(getClass().getClassLoader().getResourceAsStream(iconPath));
                creditCard.setCardType("Unknown");
                break;
        }

        cardImage.setImage(icon);
    }

    @Override
    public void shoppingCartChanged(CartEvent cartEvent) {
        cartEvent.getShoppingItem();
    }
}
