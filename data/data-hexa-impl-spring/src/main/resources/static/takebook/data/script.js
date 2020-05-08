
const URL_LIST = "/takebook/rest/data/book/";
const URL_SEARCH = "/takebook/rest/data/book/search/";

var listPanel = new Vue({
    el: '#listPanel',
    data: {
        list : [],
        searchValue : ""
    },
    created: function () {
        this.refresh();
    },
    methods: {
        refresh: function () {
            axios.get(URL_LIST).then(function (response) {
                listPanel.list = response.data;
            }).catch(function (error) {
                console.log(error);
            });
        },
        search: function() {
            var request = JSON.stringify({
                value: listPanel.searchValue
            });
            /*axios.post(URL_SEARCH, request).then(function (response) {
                listPanel.list = response.data;
            }).catch(function (error) {
                console.log(error);
            });*/

            axios.post(URL_SEARCH, request, {
                headers: {
                    'Content-Type': 'application/json',
                }
            }).then(function (response) {
              listPanel.list = response.data;
            }).catch(function (error) {
              console.log(error);
            });
        }
      }
});

