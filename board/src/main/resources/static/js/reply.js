var replyManager = (function () {
  var getAll = function (obj, callback) {
    console.log("Get All...");
    $.getJSON("/replies/" + obj, callback);
};

  var add = function (obj, callback) {
    console.log("Add...");
  };

  var update = function (obj, callback) {
    console.log("Update...");
  };

  var remove = function (obj, callback) {
    console.log("Remove...");
  };

  return {
    getAll : getAll,
    add : add,
    update : update,
    remove : remove
  }
})();
