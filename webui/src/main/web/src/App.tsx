import React from "react";
import "./App.css";
import Header from "./components/Header";
import Navigation from "./components/Navigation";
import MainBody from "./components/MainBody";
import Footer from "./components/Footer";
import "tachyons/css/tachyons.min.css";

const App = () => (
  <div className="App">
    <Header />
    <div className="cf">
        <Navigation />
        <MainBody />
    </div>
    <Footer />
  </div>
);

export default App;
