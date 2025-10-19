package pages;

import com.codeborne.selenide.ElementsCollection;
import constants.Urls;
import components.AdCard;
import components.Header;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class ProfilePage {

    private final ElementsCollection advertisementCards = $$(".card");

    private Header header;

    public ProfilePage() {
        this.header = new Header();
    }

    public Header getHeader() {
        return header;
    }

    public ProfilePage openPage() {
        open(Urls.PROFILE_PAGE_URL);
        return this;
    }

    public List<AdCard> getAllAdvertisements() {
        return advertisementCards.stream()
                .map(AdCard::new)
                .collect(Collectors.toList());
    }

    public ProfilePage shouldHaveAdvertisements() {
        advertisementCards.first().shouldBe(visible);
        return this;
    }
}