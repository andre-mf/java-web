public class Teste {

    private String nome;
    private int idade;
    
        public String getNome() {
            return this.nome;
        }
    
        public int getIdade() {
            return this.idade;
        }
    
        public void setNome(String nome) {
            this.nome = nome;
        }
    
        public void setIdade(int idade) {
            this.idade = idade;
        }

    public static void main(String[] args) {
        Teste andre = new Teste();

        andre.setNome("Andre");
        andre.setIdade(33);
        
        System.out.println("O nome armazenado no objeto eh " + andre.getNome() + ", a idade definida eh " + andre.getIdade() + " anos.");
        
    }
}