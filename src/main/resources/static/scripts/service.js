function getQuestionable() {
     $.ajax({
             type: "get",
             url: "http://localhost:11000/ui/audit/summaries"
         })
         .done(function (questionableList) {
             var questionableRow = "";

             var count = questionableList.length;
             $("#questionableTab a").append(" (" + count + ")");

             for (var i = 0; i < questionableList.length; i++) {

                 questionableRow += "<tr>";
                 var currentQuestionable = questionableList[i];

                 var profileName = currentQuestionable['profileName'];
                 questionableRow += ("<td>" + profileName + "</td>");

                 var profileEmail = currentQuestionable['profileEmail'];
                 questionableRow += ("<td>" + profileEmail + "</td>");

                 var siteName = currentQuestionable['siteName'];
                 questionableRow += ("<td>" + siteName + "</td>");

                 var reasons = currentQuestionable['findings'][0]['ruleName'];
                 questionableRow += ("<td>" + reasons + "</td>");

                 questionableRow += ("<td><input type='checkbox' ></td>");

                 questionableRow += "</tr>";
             }
             $("#questionable table").append(questionableRow);
         });
 }