import { faEdit, faList } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import axios from 'axios';
import React, { Component } from 'react';
import { ButtonGroup, Card, Table } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import InfoMessages from './InfoMessages';
import './Style.css';


export default class BookList extends Component {

    constructor(props) {
        super(props);
        this.state = {
            books : [],
            
        };
    }

   
    componentDidMount() {
        this.findAllBooks();
    }


    // findAllBooks() {
        
        
    //     axios.get("http://localhost:9999/libraries/books")
    //         .then(response =>{ 
    //             console.log("tets response"+response.data)
    //            return response.data;
    //         })
    //         .then((data) => {
    //             console.log("Data :"+data);
    //             this.setState({
    //                 books: data.content
                    
    //             });
    //         });
    // };


    findAllBooks() {
       
        let config = {
          method: 'GET',
          headers: {
            'Content-Type': 'application/json'
          }
        }
        
      
        fetch(`http://localhost:9999/libraries/1/books`, config)
          .then(response => response.json())
          .then(data => {
            this.setState({
              books: data
            });
          })
          .catch(error => {
            console.log(error.message);
          })
      }

   

    deleteBook = (bookId) => {
        axios.delete("http://localhost:8081/rest/books/"+bookId)
            .then(response => {
                if(response.data != null) {
                    this.setState({"show":true});
                    setTimeout(() => this.setState({"show":false}), 3000);
                    this.setState({
                        books: this.state.books.filter(book => book.id !== bookId)
                    });
                } else {
                    this.setState({"show":false});
                }
            });
    };

  



   

    render() {
        const {books} = this.state;

        return (
            <div>
                <div style={{"display":this.state.show ? "block" : "none"}}>
                    <InfoMessages show = {this.state.show} message = {"Book Deleted Successfully."} type = {"danger"}/>
                </div>
                <Card className={"border border-dark bg-dark text-white"}>
                    <Card.Header>
                        <div style={{"float":"left"}}>
                            <FontAwesomeIcon icon={faList} /> Book List
                        </div>
                        
                    </Card.Header>
                    <Card.Body>
                        <Table bordered hover striped variant="dark">
                            <thead>
                                <tr>
                                  <th>Name</th>
                                  <th>Author</th>
                                 <th>Language</th>
                                  <th>Genre</th>
                                  <th>Actions</th>
                                </tr>
                              </thead>
                              <tbody>
                                {
                                    books.length === 0 ?
                                    <tr align="center">
                                      <td colSpan="7">No Books Available.</td>
                                    </tr> :
                                    books.map((book) => (
                                    <tr key={book.id}>
                                        <td>{book.name}</td>
                                         <td>{book.author}</td>
                                        <td>{book.language}</td>
                                        <td>{book.genre}</td>
                                        <td>
                                            <ButtonGroup>
                                                <Link to={"edit/"+book.id} className="btn btn-sm btn-outline-primary"><FontAwesomeIcon icon={faEdit} /></Link>{' '}
                                            </ButtonGroup>
                                        </td>
                                    </tr>
                                    ))
                                }
                              </tbody>
                        </Table>
                    </Card.Body>
                </Card>
            </div>
        );
    }
}