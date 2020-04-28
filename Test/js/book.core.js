var id = 0;
var app = new Vue({
	el: '#app',
	data: {
		book: {
			id: "",
			author: "",
			name: "",
			price: ""
		},
		books: [{
				id: 1,
				author: "刘慈欣",
				name: "三体全集123",
				price: 50.0
			},
			{
				id: 2,
				author: "唐家三少",
				name: "斗罗大陆全集1-14册全套",
				price: 300.0
			},
			{
				id: 3,
				author: "烽火戏诸侯",
				name: "雪中悍刀行1-20册全套",
				price: 500.0
			}
		],
		search: ""
	},
	methods: {
		addBook: function() {
			this.book.id = this.books.length + 1;
			this.books.push(this.book);
			this.book = {};
		},
		delBook: function(book) {
			var blength = this.books.length;
			this.books.splice(book.id - 1, 1);
			for (var i = 0; i < blength; i++) {
				if (book.id < this.books[i].id) {
					this.books[i].id -= 1;
				}
			}
		},
		updateBook: function(book) {
			$("#add-book").css("display", "none");
			$("#update-book").css("display", "block");
			id = book.id;
		},
		updatesBook: function() {
			this.book.id = id;
			this.books.splice(id - 1, 1, this.book);
			$("#add-book").css("display", "block");
			$("#update-book").css("display", "none");
			this.book = {};
		}
	},
	computed: {
		filterBooks: function() {
			var books = this.books;
			var search = this.search;
			return books.filter(function(book) {
				return book.name.toLowerCase().indexOf(search.toLowerCase()) != -1;
			})
		}
	}
});
