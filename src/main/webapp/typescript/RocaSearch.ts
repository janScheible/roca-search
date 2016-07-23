class RocaSearch {
	static initialize() {
		function beautifySummaries() {
			$('details > summary').each(function(index, summaryEl) {
				let summary = $(summaryEl);				
				let summaryText : string = summary.text();
				
				// convert the URLs to Markdown syntax
				if (summaryText.indexOf('[http://') >= 0) {
					summaryText = summaryText.replace('[http://', '[<http://');
					summaryText = summaryText.trim().replace(/]$/, '>]');
				}
				
				summary.html(marked(summaryText));
				summary.html(summary.children('p').html());
				summary.parent().attr('open', 'open');
			});
		};
		beautifySummaries();
		
        function upgradeForm(formEl: JQuery, targetEl: JQuery) {
            formEl.find('button').click(function(event) {
                event.preventDefault();

                let formEl = $(this).closest('form');

                let formData = formEl.serialize();
                formData = formData + (formData.length ? '&' : '') + 'resource-only=true';

                $.ajax({
                    type: formEl.attr('method'),
                    url: formEl.attr('action'),
                    data: formData,
					headers: {
						'resource-only': true
					},
                    success: function(html: string) {
                        var dataEl = $(html.replace('<body', '<body><div id="body-helper-id"').replace('</body>', '</div></body>')).filter('#body-helper-id').children().first();

                        targetEl.replaceWith(dataEl);
						beautifySummaries();
						
						history.pushState(null, null, dataEl.attr('data-resource-url'));
                    }
                });
            });
        };

		// NOTE Let's be pretty picky - only if History API is avialable let's use AJAX
		if (Modernizr.history) {
			window.onpopstate = function() {
				window.location = window.location;
			};

			upgradeForm($('form#search'), $('section#search-section'));
		}
    }
}

RocaSearch.initialize();