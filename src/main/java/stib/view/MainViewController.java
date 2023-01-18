/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stib.view;

import g54018.stib.model.dto.FavoritesDto;
import g54018.stib.model.dto.LinesDto;
import g54018.stib.model.dto.StationsDto;
import g54018.stib.model.model.Node;
import g54018.stib.model.repository.exception.RepositoryException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.List;
import java.util.Set;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.util.Pair;
import org.controlsfx.control.SearchableComboBox;
import presenter.Presenter;

/**
 *
 * @author basile
 */
public class MainViewController implements Initializable {

    private Presenter presenter;

    @FXML
    private ImageView logoStib;

    @FXML
    private ImageView stibPathImage;

    @FXML
    private Label originLabel;

    @FXML
    private ComboBox favoriteCombo;

    @FXML
    private Button chooseFavorite;

    @FXML
    private TextField nameFavorite;

    @FXML
    private Button addFavorite;

    @FXML
    private Button modFavorite;

    @FXML
    private Button delFavorite;

    @FXML
    private Label destinationLabel;

    @FXML
    private SearchableComboBox origineCombo = new SearchableComboBox();

    @FXML
    private SearchableComboBox destinationCombo = new SearchableComboBox();

    @FXML
    private Button searchButton;

    @FXML
    private Label searchProgressLabel;

    @FXML
    private Label searchCountPathResult;

    @FXML
    private TableView tablePath = new TableView();

    @FXML
    private TableColumn<ColumnParsingTable, String> stationTableColumn = new TableColumn<>();

    @FXML
    private TableColumn<ColumnParsingTable, String> ligneTableColumn = new TableColumn<>();

    public MainViewController() throws RepositoryException {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        stationTableColumn.setCellValueFactory(new PropertyValueFactory<>("stationTableColumn"));
        ligneTableColumn.setCellValueFactory(new PropertyValueFactory<>("ligneTableColumn"));
        chooseImage(logoStib, "logoStib.png");
        chooseImage(stibPathImage, "metro.gif");
    }

    @FXML
    private void searchButtonHandle(ActionEvent event) {
        try {
            presenter.searchPathTodestination();
        } catch (RepositoryException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void chooseFavoriteHandle(ActionEvent event) {
        if (getFavorite() != null) {
            origineCombo.getSelectionModel().select(getFavorite().getOrigin());
            destinationCombo.getSelectionModel().select(getFavorite().getDestination());
        }
    }

    @FXML
    private void addFavoriteHandle(ActionEvent event) {
        presenter.addFavorite();
    }

    @FXML
    private void delFavoriteHandle(ActionEvent event) {
        presenter.delFavorite();
    }

    @FXML
    private void modFavoriteHandle(ActionEvent event) {
        presenter.modFavorite();
    }

    @FXML
    private void favoriteOnClickedCombo(ActionEvent event) {
        if (getFavorite() != null) {
            Platform.runLater(
                () -> {
                    origineCombo.getSelectionModel().select(getFavorite().getOrigin().getName());
                    destinationCombo.getSelectionModel().select(getFavorite().getDestination().getName());
                    nameFavorite.setText(getFavorite().getName());
            });
        }
    }

    public void setSearchStatus(String message) {
        searchProgressLabel.setText(message);
    }

    private void chooseImage(ImageView iv, String image) {
        InputStream input = null;
        try {
            input = new FileInputStream(getClass().getClassLoader().getResource("./stib/" + image).getFile());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Image schemeImage = new Image(input);
        iv.setImage(schemeImage);

    }

    public void fillSearchableComboBox(Set<Node> stations) {
        origineCombo.setItems(FXCollections.observableArrayList(stations));
        destinationCombo.setItems(FXCollections.observableArrayList(stations));
    }

    public void fillFavoritesComboBox(List<FavoritesDto> favorites) {
        favoriteCombo.getItems().clear();
        favoriteCombo.setItems(FXCollections.observableArrayList(favorites));
    }

    public void fillTableOfDestination(List<Pair<StationsDto, List<LinesDto>>> listStation) {
        tablePath.getItems().clear();
        Platform.runLater(
                () -> {
                    int numberOfStation = 0;
                    for (Pair<StationsDto, List<LinesDto>> pair : listStation) {
                        ColumnParsingTable std = new ColumnParsingTable(pair.getKey().getName(), pair.getValue());
                        tablePath.getItems().add(std);
                        numberOfStation++;
                    }
                    searchProgressLabel.setText("Recherche terminée");
                    searchCountPathResult.setText("Nombre de stations : " + numberOfStation);
                }
        );
    }

    public StationsDto getOrigin() {
        if (origineCombo.getValue() == null) {
            return null;
        }
        Node node= (Node) origineCombo.getValue();
        return node.getStation();
    }

    public StationsDto getDestination() {
        if (destinationCombo.getValue() == null) {
            return null;
        }
        Node node= (Node) destinationCombo.getValue();
        return node.getStation();
    }

    public String getFavoriteString() {
        if (favoriteCombo.getValue() == null) {
            return null;
        }
        return favoriteCombo.getValue().toString();
    }

    public FavoritesDto getFavorite() {
        if (favoriteCombo.getValue() == null) {
            return null;
        }
        return (FavoritesDto) favoriteCombo.getValue();
    }

    public String getNameFavorite() {
        return nameFavorite.getText();
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

}
