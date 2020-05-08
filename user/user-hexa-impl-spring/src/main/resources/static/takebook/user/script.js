
const URL_LIST = "/takebook/rest/user/";

var listPanel = new Vue({
    el: '#listPanel',
    data: {
        list : []
    },
    created: function () {
        axios.get(URL_LIST).then(function (response) {
            listPanel.list = response.data;
        }).catch(function (error) {
            console.log(error);
        });
    }
});