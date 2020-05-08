
const URL_USER_LIST = "/takebook/rest/user/";

const URL_LIBRARY_LIST = "/takebook/rest/library/";
const URL_SHELF_LIST = "/takebook/rest/library/shelf/";

const URL_BOOK_LIST = "/takebook/rest/library/book/";
const URL_BOOK_PUT_AWAY = "/takebook/rest/library/book/putAway/";
const URL_BOOK_RETURN = "/takebook/rest/library/book/return/";
const URL_BOOK_BORROW = "/takebook/rest/library/book/borrow/";

var detailsBookFunction = function(book) {
    switch(book.state) {
        case "BORROWED":
            return "Emprunté par " + book.borrower.fullName;
        case "RETURNED":
            return "Retourné à la librairie " + book.library.name;
        case "STORED":
            return "Rangé dans l'étagère " + book.shelf.label;
        default:
            return "";
    }
}

var putAwayForm = function(book, shelves) {
    var options = "";
    shelves.forEach(function(element) {
      options += "<option value=\"" + element.id +  "\">" + element.label +  "</option>";
    });
    return "<div>Merci de saisir l'étagère de la bibliotheque " + book.library.name + "</div>"
           + "<div><select id=\"selectShelf\">"
           + options
           + "</select></div>"
           + "<button onclick=\"putAwaySubmit('" + book.id + "')\">Valider</button>";
}



var putAwaySubmit = function(bookId) {
    var select = document.getElementById("selectShelf");
    var shelfId = select.options[select.selectedIndex].value;
    axios.post(URL_BOOK_PUT_AWAY, {
        "book": {
            "id": bookId
        },
        "shelf": {
            "id": shelfId
        }
    }).then(function (response) {
       Modal.close();
       booksPanel.initBooks();
    }).catch(function (error) {
        console.log(error);
    });
}

var returnSubmit = function(bookId) {
    var select = document.getElementById("selectLibrary");
    var libraryId = select.options[select.selectedIndex].value;
    axios.post(URL_BOOK_RETURN, {
        "book": {
            "id": bookId
        },
        "library": {
            "id": libraryId
        }
    }).then(function (response) {
       Modal.close();
       booksPanel.initBooks();
    }).catch(function (error) {
        console.log(error);
    });
}


var borrowSubmit = function(bookId) {
     var select = document.getElementById("selectBorrower");
     var borrowerId = select.options[select.selectedIndex].value;

    axios.post(URL_BOOK_BORROW, {
        "book": {
            "id": bookId
        },
        "borrower": {
            "id": borrowerId
        }
    }).then(function (response) {
       Modal.close();
       booksPanel.initBooks();
    }).catch(function (error) {
        console.log(error);
    });
}

var booksPanel = new Vue({
    el: '#listPanel',
    data: {
        libraries: [],
        shelves: [],
        books : [],
        borrowers: []
    },
    created: function () {
        this.initBooks();
        this.initLibraries();
        this.initBorrowers();
    },
    methods: {
        initBooks: function () {
            axios.get(URL_BOOK_LIST).then(function (response) {
                booksPanel.books = response.data;
            }).catch(function (error) {
                console.log(error);
            });
        },
        initLibraries: function () {
            axios.get(URL_LIBRARY_LIST).then(function (response) {
                booksPanel.libraries = response.data;
            }).catch(function (error) {
                console.log(error);
            });
        },
        initBorrowers: function () {
            axios.get(URL_USER_LIST).then(function (response) {
                booksPanel.borrowers = response.data;
            }).catch(function (error) {
                console.log(error);
            });
        },
        detailsBook: function(book) {
            return detailsBookFunction(book);
        },
        borrow : function(book) {
            var options = "";
            this.borrowers.forEach(function(element) {
              options += "<option value=\"" + element.id +  "\">" + element.firstName + " " + element.lastName +  "</option>";
            });

            var htmlContent = "<div>Merci de saisir le nom de la personne qui emprunte le livre</div>"
                   + "<div><select id=\"selectBorrower\">"
                                              + options
                                              + "</select></div>"
                   + "<button onclick=\"borrowSubmit('" + book.id + "')\">Valider</button>";

            Modal.open({
                content: htmlContent,
                draggable: true
            });
        },
        putAway : function(book) {
            axios.get(URL_SHELF_LIST + book.library.id + "/").then(function (response) {
                Modal.open({
                    content: putAwayForm(book, response.data),
                    draggable: true
                });
            }).catch(function (error) {
                console.log(error);
            });
        },
        ret: function(book) {
            var options = "";
            this.libraries.forEach(function(element) {
              options += "<option value=\"" + element.id +  "\">" + element.name +  "</option>";
            });
            var htmlContent = "<div>Merci de saisir la bibliotheque</div>"
                   + "<div><select id=\"selectLibrary\">"
                   + options
                   + "</select></div>"
                   + "<button onclick=\"returnSubmit('" + book.id + "')\">Valider</button>";

            Modal.open({
                content: htmlContent,
                draggable: true
            });
        }
      }
});
