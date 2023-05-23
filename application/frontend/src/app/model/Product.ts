export class Product {
    public id: number | null = null;
    public name: string = "";
    public price: number = -1;
    public description: string = "";
    public category: string = "";
    public userId: number | null = null;

    public constructor(
        id: number | null,
        name: string,
        description: string,
        category: string,
        userId: number | null,
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.userId = userId
    }
}