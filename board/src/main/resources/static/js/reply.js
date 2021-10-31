var replyManager = (function () {
  var getAll = function (obj, callback) {
    console.log("Get All...");
    $.getJSON("/replies/" + obj, callback);
};

  var add = function (obj, callback) {
    console.log("Add...");

    $.ajax({
      type : "post",
      url : "/replies/" + obj.bno,
      data : JSON.stringify(obj),
      dataType : "json",
      contentType : "application/json",
      success : callback
    });
  };

  var update = function (obj, callback) {
    console.log("Update...");
  };

  var remove = function (obj, callback) {
    console.log("Remove...");

    $.ajax({
      type : "delete",
      url : "/replies/" + obj.bno + "/" + obj.rno,
      dataType : "json",
      contentType : "application/json",
      success : callback
    });
  };

  return {
    getAll : getAll,
    add : add,
    update : update,
    remove : remove
  }
})();
