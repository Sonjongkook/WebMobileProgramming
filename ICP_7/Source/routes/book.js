var express = require('express');
var router = express.Router();
var Book = require('../models/Book.js');

/* GET ALL BOOKS */
router.get('/', function (req, res, next) {
  Book.find(function (err, products) {
    if (err) return next(err);
    res.json(products);
  });
});

/* GET SINGLE BOOK BY ID */
router.get('/:id', function (req, res, next) {
  Book.findById(req.params.id, function (err, post) {
    if (err) return next(err);
    res.json(post);
  });
});

/* SAVE BOOK */
router.post('/', function (req, res, next) {
  Book.create(req.body, function (err, post) {
    if (err) return next(err);
    res.json(post);
  });
});

/* UPDATE BOOK */
router.put('/:id', function (req, res, next) {
  //id is given when request has been made
  let id = {_id: req.params.id};
  let values = { $set: req.body };
  //update book model by using updateone method
  Book.updateOne(id, values, function (error, post) {
    if (error)  {return next(error)}
    res.json(post);
  });

});

/* DELETE BOOK */

router.delete('/:id', function (req, res, next) {
  let id = {_id: req.params.id};
  //delete the book model by using delete one method
  Book.deleteOne(id, function (error, post) {
    if (error)  {return next(error)}
    //response with json object
    res.json(post);
  });
});



module.exports = router;
