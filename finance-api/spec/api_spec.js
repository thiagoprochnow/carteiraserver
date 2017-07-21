handlers = require('../handler.js');

describe('Finance API', function() {

    describe('handlers', function() {

        beforeEach(function() {
        });

        it('stocksCreateEvent refuses empty body', function() {
            var response = '';
            var context = '';
            var event = '';
            handlers.stocksCreateEvent(event, context, function(error, ret) {
                response = error;
            });
            expect(response).toEqual(new Error('Body need contains item as JSON'));
        });

        it('stocksCreateEvent refuses non JSON body', function() {
            var response = '';
            var context = '';
            var event = {};
            event.body = "this is not a JSON";
            handlers.stocksCreateEvent(event, context, function(error, ret) {
                response = error;
            });
            expect(response).toEqual(new Error('Body has not a valid JSON'));
        });
    });
    describe('Stocks modules', function() {
    });
});
