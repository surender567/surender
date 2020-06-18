import React, {Component} from 'react';

import {Card, Form, Button, Col} from 'react-bootstrap';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {faSave, faPlusSquare, faUndo, faList, faEdit} from '@fortawesome/free-solid-svg-icons';
import InfoMessages from './InfoMessages';
import axios from 'axios';

export default class Book extends Component {

    constructor(props) {
        super(props);
        this.state = this.initialState;
        this.state = {
            genres: [],
            languages : [],
            show : false
        };
        this.bookChange = this.bookChange.bind(this);
        this.submitBook = this.submitBook.bind(this);
    }

    initialState = {
        id:'', name:'', author:'',  language:'', genre:''
    };

    componentDidMount() {
        const bookId = +this.props.match.params.id;
        if(bookId) {
            this.findBookById(bookId);
        }
    }

 
    findBookById = (bookId) => {
        axios.get("http://localhost:9999/books/"+bookId)
            .then(response => {
                if(response.data != null) {
                    this.setState({
                        id: response.data.id,
                        name: response.data.name,
                        author: response.data.author,
                        language: response.data.language,
                        genre: response.data.genre
                    });
                }
            }).catch((error) => {
                console.error("Error - "+error);
            });
    };

    resetBook = () => {
        this.setState(() => this.initialState);
    };

    
    submitBook = event => {
        event.preventDefault();

        const book = {
            name: this.state.name,
            author: this.state.author,
            language: this.state.language,
            genre: this.state.genre
        };

        axios.post("http://localhost:9999/libraries/book", book)
            .then(response => {
                if(response.data != null) {
                    this.setState({"show":true, "method":"post"});
                    setTimeout(() => this.setState({"show":false}), 3000);
                } else {
                    this.setState({"show":false});
                }
            });

        this.setState(this.initialState);
    };

    updateBook = event => {
        event.preventDefault();

        const book = {
            id: this.state.id,
            name: this.state.name,
            author: this.state.author,
            language: this.state.language,
            genre: this.state.genre
        };

        axios.put(`http://localhost:9999//libraries/1/updatebook/${this.state.id}`, book)
            .then(response => {
                if(response.data != null) {
                    this.setState({"show":true, "method":"put"});
                    setTimeout(() => this.setState({"show":false}), 3000);
                    setTimeout(() => this.bookList(), 3000);
                } else {
                    this.setState({"show":false});
                }
            });

        this.setState(this.initialState);
    };

    bookChange = event => {
        this.setState({
            [event.target.name]:event.target.value
        });
    };

    bookList = () => {
        return this.props.history.push("/list");
    };

    render() {
        const {name, author, language, genre} = this.state;

        return (
            <div>
                <div style={{"display":this.state.show ? "block" : "none"}}>
                    <InfoMessages show = {this.state.show} message = {this.state.method === "put" ? "Book Updated Successfully." : "Book Saved Successfully."} type = {"success"}/>
                </div>
                <Card className={"border border-dark bg-dark text-white"}>
                    <Card.Header>
                        <FontAwesomeIcon icon={this.state.id ? faEdit : faPlusSquare} /> {this.state.id ? "Update Book" : "Add New Book"}
                    </Card.Header>
                    <Form onReset={this.resetBook} onSubmit={this.state.id ? this.updateBook : this.submitBook} id="bookFormId">
                        <Card.Body>
                            <Form.Row>
                                <Form.Group as={Col} controlId="formGridTitle">
                                    <Form.Label>Name</Form.Label>
                                    <Form.Control required autoComplete="off"
                                        type="test" name="name"
                                        value={name} onChange={this.bookChange}
                                        className={"bg-dark text-white"}
                                        placeholder="Enter Book Name" />
                                </Form.Group>
                                <Form.Group as={Col} controlId="formGridAuthor">
                                    <Form.Label>Author</Form.Label>
                                    <Form.Control required autoComplete="off"
                                        type="test" name="author"
                                        value={author} onChange={this.bookChange}
                                        className={"bg-dark text-white"}
                                        placeholder="Enter Book Author" />
                                </Form.Group>
                            </Form.Row>
                           
                            <Form.Row>
                                <Form.Group as={Col} controlId="formGridLanguage">
                                    <Form.Label>Language</Form.Label>
                                    <Form.Control required autoComplete="off"
                                        type="test" name="language"
                                        value={language} onChange={this.bookChange}
                                        className={"bg-dark text-white"}
                                        placeholder="Enter Book Language" />
                                </Form.Group>

                                <Form.Group as={Col} controlId="formGridGenre">
                                    <Form.Label>Genre</Form.Label>
                                    <Form.Control required autoComplete="off"
                                        type="test" name="genre"
                                        value={genre} onChange={this.bookChange}
                                        className={"bg-dark text-white"}
                                        placeholder="Enter Genre" />
                                </Form.Group>
                            </Form.Row>
                        </Card.Body>
                        <Card.Footer style={{"textAlign":"right"}}>
                          <Button size="sm" variant="success" type="submit">
                                <FontAwesomeIcon icon={faSave} /> {this.state.id ? "Update" : "Save"}
                            </Button>{' '}
                            <Button size="sm" variant="info" type="reset">
                                <FontAwesomeIcon icon={faUndo} /> Reset
                            </Button>{' '}
                            <Button size="sm" variant="info" type="button" onClick={this.bookList.bind()}>
                                <FontAwesomeIcon icon={faList} /> Book List
                            </Button>
                        </Card.Footer>
                    </Form>
                </Card>
            </div>
        );
    }
}